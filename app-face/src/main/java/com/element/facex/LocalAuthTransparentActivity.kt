package com.element.facex

import android.util.Log
import com.element.camera.ElementFaceAuthActivity

class LocalAuthTransparentActivity : ElementFaceAuthActivity() {

    companion object {
        private const val TAG = "LocalAuthTransparent"
    }

    val userId: String?
        get() = intent.getStringExtra(EXTRA_ELEMENT_USER_ID)

    override fun onVerify() {
        Log.d(TAG, "user verified: $userId")
        finish()
    }

    override fun onNotVerify(reason: String?) {
        Log.d(TAG, "user not verified: $userId")
        finish()
    }
}