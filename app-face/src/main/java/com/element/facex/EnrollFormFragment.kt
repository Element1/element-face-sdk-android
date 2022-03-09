package com.element.facex

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.preference.PreferenceManager
import com.element.camera.UserInfo
import com.element.common.text.ComTextUtils

class EnrollFormFragment : DialogFragment() {

    private val processing: String?
        get() = PreferenceManager.getDefaultSharedPreferences(context)
            .getString("processing", "Local")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_enroll_form, container, false).also {
        it.findViewById<Button>(R.id.enroll).apply {
            setOnClickListener { view -> clickEnroll(view as Button) }
            setText(
                if ("Local" == processing) R.string.local_enroll
                else R.string.server_enroll
            )
        }
    }

    @Synchronized
    private fun clickEnroll(button: Button) {
        button.isEnabled = false
        Handler(Looper.getMainLooper()).postDelayed({ button.isEnabled = true }, 500)

        val name = (view?.findViewById<View>(R.id.name) as? EditText)?.text?.toString() ?: run {
            Toast.makeText(activity, R.string.error_msg_empty_fields, Toast.LENGTH_SHORT).show()
            dismiss()
            return
        }
        val userId = (view?.findViewById<View>(R.id.userId) as? EditText)?.text?.toString() ?: run {
            Toast.makeText(activity, R.string.error_invalid_user_id, Toast.LENGTH_SHORT).show()
            dismiss()
            return
        }

        val userInfo = if (ComTextUtils.isEmptyOrWhitespace(userId)) {
            UserInfo.builder().setName(name).enroll(activity)
        } else {
            UserInfo.builder().setId(userId).setName(name).enroll(activity)
        }
        (activity as FaceExMainActivity?)?.startEnroll(userInfo.userId)
        dismiss()
    }
}