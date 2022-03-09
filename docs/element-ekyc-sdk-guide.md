![element](images/element.png "element")
# Element Cloud eKYC SDK

The Element Cloud eKYC SDK is lite version of the combination of the Element Face SDK and the Element Card SDK. It creates biometric models that can be used to authenticate users as the Element Face SDK, and enables ID card / document scanning in order to obtain user information from optical character recognition (OCR) as the Element Card SDK. Internet connection is required, as the image processing and data management is happening in the cloud.

## Version Support
### Android device, development environments, and Element Dashboard
- The minimum Android device requirement is Android 5.0 or API 21 (Android OS Lollipop and up). A minimum of 2GB of RAM is recommended for optimal performance.
- Android Target SDK Version 30, Build Tool Version 30.0.2, and AndroidX.
- Please refer to [prerequisites](prerequisites.md) to configure the development environments.
- The EAK for the SDK requires extra settings. This step has not automated yet. Please contact Element with your EAK if you would like to try the SDK.

### Dependencies
- Kotlin Coroutines: 1.5.2
- AndroidX Kotlin Coroutines: 1.5.2

References for dependencies can be found in the sample project at [app-ekyc](../app-ekyc/build.gradle).

## SDK Integration
### Initialize the Element Cloud eKYC SDK
Create a class which extends [android.app.Application](https://developer.android.com/reference/android/app/Application), and initialize the `ElementEKycSDK` in `onCreate()` method:
```
open class CardApplication : Application() {
  override fun onCreate() {
      super.onCreate()
      ElementEKycCloudSDK.initSDK(this)
  }
}
```

### Selfie enrollment & authentication
The `ElementCloudFaceActivity` is the main class for user selfie enrollment and authentication. It handles the flow, UI, and the communication with the backend servers.

- Declares the Activity in the manifest,
```
<manifest>
  .....
  <application android:name=".MainApplication">
    .....
    <activity android:name="com.element.camera.ElementCloudFaceActivity"
      android:clearTaskOnLaunch="true" />
    .....
  </application>
</manifest>
```

- Starts the activity with an `Intent`. The `EXTRA_URL` and `EXTRA_ELEMENT_USER_ID` are required to be specified in the extras of the `Intent`. For enrollment, `EXTRA_CAPTURE_MODE` is also needed in the `Intent`.
```
startActivity(
  Intent(baseContext, ElementCloudFaceActivity::class.java).apply {
    putExtra(ElementCloudFaceActivity.EXTRA_URL, getString(R.string.user_enroll_url))
    putExtra(ElementCloudFaceActivity.EXTRA_ELEMENT_USER_ID, myUserId)
    putExtra(ElementCloudFaceActivity.EXTRA_CAPTURE_MODE, "ENROLL")
  }
)
```

- Overrides the folling callback methods in order to display customized UI or perform alternative flows.
  - `onHttpStart`: Local face detection completed successfully. The app starts posting to the backend server now.
  - `onHttpError`: The server was unable to process the request, or network connections failed.
  - `onFaceDetectFailed`:  No face detected.
  - `onEnrollResult`: Received the enrollment results from the server.
  - `onAuthResult`: Received the authentication results from the server.

### Document scan
The `ElementCloudDocSelectActivity` and `ElementCloudCardScanActivity` handle documention from selection, sanning, confirmation, and posting to the server.

- Declares the Activity in the manifest,
```
<manifest>
  .....
  <activity
    android:name="com.element.camera.ElementCloudDocSelectActivity"
    android:label=""
    android:screenOrientation="portrait"
    android:theme="@style/AppTheme.NoActionBar" />

  <activity android:name="com.element.camera.ElementCloudCardScanActivity"
    android:screenOrientation="portrait" />
</manifest>
```

- Starts the document scan process with an `Intent`. The `EXTRA_URL` and `EXTRA_ELEMENT_USER_ID` are also required in the extras of the `Intent`.
```
startActivity(
  Intent(baseContext, ElementCloudDocSelectActivity::class.java).apply {
    putExtra(ElementCloudFaceActivity.EXTRA_URL, getString(R.string.user_auth_url))
    putExtra(ElementCloudFaceActivity.EXTRA_ELEMENT_USER_ID, myUserId)
  }
)
```

### User management
Similar to the Element Face SDK, the Cloud eKYC SDK provides a few CRUD async tasks for user management.

- Creation
```
ElementEKycUserCreateTask(
  baseContext,
  getString(R.string.user_create_url),
  { id ->
    PrefsManager.getInstance(this).putValue(PREFS_KEY_USER_ID, id)
    updateUi()
    toastMessage("User created")
  },
  { code, json ->
    toastMessage("ERROR code: $code -- $json")
  }
).doPost()
```

- Status check
```
ElementEKycUserStatusTask(
  baseContext,
  getString(R.string.user_status_url, myUserId),
  { statusReceipt ->
      PrefsManager.getInstance(this).putValue(PREFS_KEY_USER_STATUS, GsonBase.toJson(statusReceipt))
      updateUi()
      toastMessage("Refreshed")
  },
  { code, json ->
      toastMessage("ERROR code: $code -- $json")
  }
).doGet()
```

### Questions?
If you have questions, please contact devsupport@discoverelement.com.
