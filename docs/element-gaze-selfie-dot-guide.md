![element](images/element.png "element")
# Element SelfieDot
The Element Face SDK (the SDK) is an API library for creating biometric models that can be used to authenticate users. This document contains information for enabling the latest Face Gaze functionality - SelfieDot. 

## Dependencies
- Requires Element Face SDK
Please refer to [Element Face SDK doc](element-face-sdk-guide.md) for details about set up and usage.

### Activity declaration
SelfieDot supports transparent Authentication so the Auth Activity should be using a Transparent theme, set in the manifest like so.
```
    <manifest>
      .....
      <application android:name=".MainApplication | 
        .....
        <activity android:name="com.element.camera.LocalAuthTransparentActivity"
        android:theme="@style/Theme.Transparent"/>
        .....
      </application>
    </manifest>
```

Here is an example of a working theme:
```
<style name="Theme.Transparent" parent="AppTheme.NoActionBar | 
        <item name="android:windowNoTitle | true</item>
        <item name="android:windowBackground | @android:color/transparent</item>
        <item name="android:colorBackgroundCacheHint | @null</item>
        <item name="android:windowIsTranslucent | true</item>
        <item name="android:windowAnimationStyle | @null</item>
    </style>
```

## Usage of the Element Activities
When starting an Enrollment or Auth Activity just add this Extra: 
 ```
 intent.putExtra(ElementFaceAuthActivity.EXTRA_UI_DELEGATE, "selfieDot")
 ```

Otherwise, this feature functions exactly the same as normal Face SDK activities. Again, please refer to [Element Face SDK doc](element-face-sdk-guide.md) for further instructions.

## Customization of resources
### String Resources

#### Face
| Resource ID | Displayed String |
| --- | --- |
| element_centerFace | Center face in frame |
| element_keepCentered | Keep face centered |
| element_watchCircles | Watch the circles |
| element_selfieTutorial | Let's take a few selfies to verify your identity |
| element_startCamera | Start Camera |
| element_start | Start |
| element_tryAgain | Try again |
| element_next | Next | | 
| element_positionFaceFrame | Center your face in the frame</string>
| element_gazeFollowDot | Follow the dot</string>
| element_moveCloser | Move closer</string>
| element_moveFurther | Move further away</string>
| element_holdFace | Keep your eyes open\nHold face steady</string>
| element_gazeFailed | Please follow the dot</string>
| element_clearFaceView | Have a clear view of your face</string>
| element_holdPhoneEyeLevel | Hold phone at eye level</string>
| element_changeLighting | Let\'s try another position with different lighting</string>
| element_ready | I\'m ready</string>
| element_howTo | How to…</string>
| element_selfieMoveMessage | We\'ll automatically take a selfie then you\'ll be prompted to move your eyes to make sure it\'s you.</string>
| element_selfieMessage | You\'ll be asked to frame your face then we\'ll snap a selfie.</string>
| element_letsGo | Let's Go</string>
| element_selfie | Selfie</string>
| element_move | Move</string>
| element_lookHere | Look here</string>
| element_holdStill | Hold still</string>
| element_positionFaceHere | Position face here</string>
| element_positionFaceInCircle | Position your face\nin the circle</string>
| element_initCamera | Initializing camera… |
| element_authLockedOut | Too many recent false attempts - please try again in %d seconds |
| element_processing | Processing… |
    
#### Card
| Resource ID | Displayed String |
| --- | --- |
| element_invalidDoc | Invalid doc: %s |
| element_scanFrontSide | Scan front side |
| element_scanBackSide | Scan back side |
| element_scanSignaturePage | Scan signature page |
| element_notificationProcessing | @string/element_processing |
| element_notificationDone | Complete! |
| element_looksGood | Looks good |
| element_reviewScanSubtitle | Check image for the following: |
| element_reviewScanNoBlur | No blur |
| element_reviewScanNoGlare | No glare |
| element_reviewScanEntireDoc | Entire ID is visible |
| element_reviewProgressTitle | Verifying your information |
| element_reviewProgressMessage | You can now close the app, we’ll notify you when the review is completed. |
| element_reviewProgressUploading | Uploading information |
| element_reviewProgressWaiting | Waiting for review |
| element_reviewProgressDone | Done! |
| element_reviewProgressCheckNow | Check now |
| element_reviewProgressLastCheck | Last checked: %1$s |
| element_signatureCreate | Let's create your\nsignature |
| element_signatureHint | Use your finger to sign |
| element_signatureClear | Clear |
| element_signatureUseImage | Use image |
| element_signatureEmpty | Please provide your signature |
    
### Color Resources
#### Face
| Resource ID | Color |
| --- | --- |
| element_textColor | ![#02364A](https://via.placeholder.com/15/02364A/000000?text=+) #02364A | 
| element_textColorGrey | ![#788081](https://via.placeholder.com/15/788081/000000?text=+) #788081 | 
| element_titleTextColor | ![#02364A](https://via.placeholder.com/15/02364A/000000?text=+) #02364A | 
| element_statusBarColor | ![#27000000](https://via.placeholder.com/15/27000000/000000?text=+) #27000000 | 
| element_negativeButtonColor | ![#FF7017](https://via.placeholder.com/15/FF7017/000000?text=+) #FF7017 | 
| element_selfieDotRingColorDark | ![#FF11BDC6](https://via.placeholder.com/15/FF11BDC6/000000?text=+) #FF11BDC6 | 
| element_selfieDotRingColor | ![#FF76E8BD](https://via.placeholder.com/15/FF76E8BD/000000?text=+) #FF76E8BD | 
| element_gazeWaveCorner | ![#009acd](https://via.placeholder.com/15/009acd/000000?text=+) #009acd |
| element_gazeWaveCornerText | @android:color/white |
| element_gazeWaveRing | ![#993452e1](https://via.placeholder.com/15/993452e1/000000?text=+) #993452e1 |
| element_gazeWaveRingBackground | ![#77ffffff](https://via.placeholder.com/15/77ffffff/000000?text=+) #77ffffff |
| element_cameraBackground | @color/black |
| element_cameraCurtain | ![#DDFFFFFF](https://via.placeholder.com/15/DDFFFFFF/000000?text=+) #DDFFFFFF |
| element_gazeFlowerCheckmark| @color/element_gazeFlowerOutlineDetected |
| element_gazeFlowerPetal | ![#6000A8FF](https://via.placeholder.com/15/6000A8FF/000000?text=+) #6000A8FF |
| element_gazeFlowerRing | ![#7C7C7C](https://via.placeholder.com/15/7C7C7C/000000?text=+) #7C7C7C |
| element_gazeFlowerOutlineDefault | ![#FFB2B2B2](https://via.placeholder.com/15/FFB2B2B2/000000?text=+) #FFB2B2B2 |
| element_gazeFlowerOutlineDetected | ![#FF00A8FF](https://via.placeholder.com/15/FF00A8FF/000000?text=+) #FF00A8FF |
| element_gazeFlowerOutlineError | ![#FFE00C0C](https://via.placeholder.com/15/FFE00C0C/000000?text=+) #FFE00C0C |
| element_gazeFlowerBackground | @color/element_cameraCurtain |
| element_gazeSelfieDotTooltip | @color/element_textColor |
| element_gazeSelfieDotOpaqueBackground | @color/white |
| element_gazeSelfieDotTranslucentBackground | ![#CC000000](https://via.placeholder.com/15/CC000000/000000?text=+) #CC000000 |

#### Card
| Resource ID | Color |
| --- | --- |
| element_textColorNotification | ![#000000](https://via.placeholder.com/15/000000/000000?text=+) #000000 | 
| element_notification_icon_color | ![#12B5BF](https://via.placeholder.com/15/12B5BF/000000?text=+) #12B5BF | 

### Dimention&Integer Resources
#### Face
| Resource ID | Value |
| --- | --- |
| element_gazeWaveDot | 70dp |
| element_gazeWaveDotTopOffset | 100dp |
| element_gazeWaveCorner | 160dp |
| element_gazeWaveCornerText | 80sp |
| element_gazeCornerTextPaddingHorizontal | 15dp |
| element_gazeCornerTextPaddingVertical | 10dp |
| gaze_pulse_interval | 180 |
| gaze_pulse_count | 5 |

### Array Resources

####  element_cardScanTitles

| Order | Default Value | Description |
| --- | --- | --- |
| 0 | element_scanFrontSide | Scan front side |
| 1 | element_scanBackSide | Scan back side |
| 2 | element_scanSignaturePage | Scan signature page |
 
- Example
  ```
    <string-array name="element_cardScanTitles | 
        <item>@string/front_side</item>
        <item>@string/back_side</item>
        <item>@string/sign_page</item>
    </string-array>
  ```

####  element_cardScanIcon

| Order | Default Value | Description |
| --- | --- | --- |
| 0 | <div style="background:black;text-align:center;padding-top:5px">![front](images/icon/icon_front.png)</div> | width:47dp<p>height:31dp |
| 1 | <div style="background:black;text-align:center;padding-top:5px">![back](images/icon/icon_back.png)</div> | width:47dp<p>height:31dp |
| 2 | <div style="background:black;text-align:center;padding-top:5px">![sign](images/icon/icon_signature.png)</div> | width:47dp<p>height:31dp |
 
- Example
  ```
    <integer-array name="element_cardScanIcon">
        <item>@drawable/ic_front_side</item>
        <item>@drawable/ic_back_side</item>
        <item>@drawable/ic_sign_side</item>
    </integer-array>
  ```

####  element_docSelectIcon

| Order | Default Value | Description |
| --- | --- | --- |
| 0 | ![passport](images/icon/passport.png) | Associated with document display name `Passport` <p>width:35dp<p>height:49dp |
| 1 | ![drive license](images/icon/drive_license.png)</div> | Associated with document display name `Driver's License` <p>width:47dp<p>height:31dp |
| 2 | ![id card](images/icon/id_card.png)</div> | Other cards<p>width:47dp<p>height:31dp |
- Notice:
 -  Every customized icon associated with document id will be applied regardless to the order of the list. 
 -  Every document whose id is not associated to a customized icon will apply default icon.
- Example
  ```
	<array name="element_docSelectIcon">
		<item>@array/document1</item>
		<item>@array/document2</item>
	</array>
	<array name="document1">
		<item>"4020080a-9e2e-47a2-9e07-534718857650"</item>
		<item>@drawable/icon_document1</item>
	</array>
	<array name="document2">
		<item>"6318ae44-5c61-4819-a2fd-0405c5581548"</item>
		<item>@drawable/icon_document2</item>
	</array>
  ```

### Questions?
If you have questions, please contact devsupport@discoverelement.com.
