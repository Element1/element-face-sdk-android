package com.element.ekycex

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.element.camera.*
import com.element.common.PermissionUtils
import com.element.common.PrefsManager
import com.element.common.base.GsonBase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

open class EKycExMainActivity : AppCompatActivity() {

    companion object {
        private const val PREFS_KEY_USER_ID = "userId"
        private const val PREFS_KEY_USER_STATUS = "userStatus"
    }

    private val myUserId: String?
        get() = PrefsManager.getInstance(this)
            .getStringValue(PREFS_KEY_USER_ID)
            .takeIf { it.isNotBlank() }

    private val myUserStatus: StatusReceipt?
        get() = PrefsManager.getInstance(this)
            .getStringValue(PREFS_KEY_USER_STATUS)
            .takeIf { it.isNotBlank() }
            .let {
                GsonBase.fromJson(it, StatusReceipt::class.java)
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT)
        setSupportActionBar(toolbar)

        createUser.setOnClickListener {
            ElementEKycUserCreateTask(
                baseContext,
                getString(R.string.user_create_url),
                { id ->
                    PrefsManager.getInstance(this).putValue(
                        PREFS_KEY_USER_ID,
                        id
                    )
                    updateUi()
                    toastMessage("User created")
                },
                { code, json ->
                    toastMessage("ERROR code: $code -- $json")
                }
            ).doPost()
        }

        selfieEnroll.setOnClickListener {
            startEnroll()
        }

        docScan.setOnClickListener {
            startDocScan()
        }

        userStatus.setOnClickListener {
            ElementEKycUserStatusTask(
                baseContext,
                getString(R.string.user_status_url, myUserId),
                { statusReceipt ->
                    PrefsManager.getInstance(this).putValue(
                        PREFS_KEY_USER_STATUS,
                        GsonBase.toJson(statusReceipt)
                    )
                    updateUi()
                    toastMessage("Refreshed")
                },
                { code, json ->
                    toastMessage("ERROR code: $code -- $json")
                }
            ).doGet()
        }

        selfieAuth.setOnClickListener {
            startAuth()
        }

        selfieAuthTransparent.setOnClickListener {
            startAuthWithTransparentBackground()
        }

        deleteUser.setOnClickListener {
            PrefsManager.getInstance(this).putValue(PREFS_KEY_USER_ID, "")
            PrefsManager.getInstance(this).putValue(PREFS_KEY_USER_STATUS, "")
            updateUi()
        }
    }

    private fun startEnroll() {
        startActivity(
            Intent(baseContext, ElementCloudFaceActivity::class.java).apply {
                putExtra(ElementCloudFaceActivity.EXTRA_URL, getString(R.string.user_enroll_url))
                putExtra(ElementCloudFaceActivity.EXTRA_ELEMENT_USER_ID, myUserId)
                putExtra(ElementCloudFaceActivity.EXTRA_CAPTURE_MODE, "ENROLL")
            }
        )
    }

    private fun startAuth() {
        startActivity(
            Intent(baseContext, ElementCloudFaceActivity::class.java).apply {
                putExtra(ElementCloudFaceActivity.EXTRA_URL, getString(R.string.user_auth_url))
                putExtra(ElementCloudFaceActivity.EXTRA_ELEMENT_USER_ID, myUserId)
            }
        )
    }

    private fun startAuthWithTransparentBackground() {
        startActivity(
            Intent(baseContext, AuthTransparentActivity::class.java).apply {
                putExtra(ElementCloudFaceActivity.EXTRA_URL, getString(R.string.user_auth_url))
                putExtra(ElementCloudFaceActivity.EXTRA_ELEMENT_USER_ID, myUserId)
            }
        )
    }

    private fun startDocScan() {
        startActivity(
            Intent(baseContext, ElementCloudDocSelectActivity::class.java).apply {
                putExtra(ElementCloudFaceActivity.EXTRA_URL, getString(R.string.card_scan_url))
                putExtra(ElementCloudFaceActivity.EXTRA_ELEMENT_USER_ID, myUserId)
            }
        )
    }

    private fun toastMessage(message: String) = Toast.makeText(
        baseContext,
        message,
        Toast.LENGTH_LONG
    ).show()

    override fun onResume() {
        super.onResume()
        PermissionUtils.verifyPermissions(
            this,
            Manifest.permission.CAMERA
        )
        updateUi()
    }

    private fun updateUi() {
        if (myUserId == null) {
            userId.text = getString(R.string.no_user)
            createUser.isEnabled = true
            selfieEnroll.isEnabled = false
            docScan.isEnabled = false
            userStatus.isEnabled = false
            selfieAuth.isEnabled = false
            selfieAuthTransparent.isEnabled = false
            deleteUser.isEnabled = false
            enrollStatus.visibility = GONE
            ocrStatus.visibility = GONE
            cardMatchStatus.visibility = GONE
        } else {
            userId.text = getString(R.string.user_id, myUserId)
            createUser.isEnabled = false
            selfieEnroll.isEnabled = true
            docScan.isEnabled = true
            userStatus.isEnabled = true
            selfieAuth.isEnabled = true
            selfieAuthTransparent.isEnabled = true
            deleteUser.isEnabled = true
            enrollStatus.visibility = VISIBLE
            ocrStatus.visibility = VISIBLE
            cardMatchStatus.visibility = VISIBLE
            myUserStatus?.let {
                enrollStatus.text = getString(R.string.enroll_status, it.enrollStatus)
                ocrStatus.text = getString(R.string.ocr_status, it.ocrStatus)
                cardMatchStatus.text = getString(R.string.card_match_status, it.cardMatchStatus)
            } ?: kotlin.run {
                enrollStatus.text = getString(R.string.enroll_status, "N/A")
                ocrStatus.text = getString(R.string.ocr_status, "N/A")
                cardMatchStatus.text = getString(R.string.card_match_status, "N/A")
            }
        }
    }
}
