package com.element.facex

import android.util.Log
import com.element.camera.ElementFaceEnrollActivity

class LocalEnrollActivity : ElementFaceEnrollActivity() {

    companion object {
        private const val TAG = "LocalEnroll"
    }

    override fun onAccountCreated() {
        Log.d(TAG, "user account created")
        finish()
    }
}