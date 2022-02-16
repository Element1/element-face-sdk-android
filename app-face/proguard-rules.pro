##################################################
# java
##################################################

-keepclassmembers class * extends java.lang.Enum {
    <fields>;
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepattributes *Annotation*

##################################################
# android & google
##################################################

-dontwarn com.google.**
-dontwarn androidx.**
-keep class * extends android.app.Activity { *; }
-keep class * extends android.app.Application { *; }
-keep class * extends android.app.Service
-keep class * extends android.content.BroadcastReceiver
-keep class com.google.** { *; }
-keep class android.** { *; }
-keep class androidx.** { *; }
-keep interface androidx.** { *; }
-keepclassmembers class  *  {
    void $$clinit();
}

##################################################
# aws
##################################################

-keep class com.amazonaws.** { *; }
-keep class com.amazon.** { *; }
-keep class com.amazonaws.services.**.*Handler
-dontwarn com.fasterxml.jackson.**
-dontwarn org.apache.commons.logging.**
-dontwarn org.apache.http.**
-dontwarn com.amazonaws.http.**
-dontwarn com.amazonaws.metrics.**

##################################################
# whiteCryption
##################################################

-dontwarn afu.org.checkerframework.**
-keep class afu.** { *; }
-keep class org.checkerframework.** { *; }
-keep class com.whitecryption.** { *; }
-keep class com.cryptanium.** { *; }
-keep @interface com.whitecryption.annotation.*
-keepattributes RuntimeInvisibleAnnotations
-keepclassmembers @interface com.whitecryption.annotation.* {
    public *;
}

##################################################
# element
##################################################

-dontwarn org.opencv.**
-keep class org.opencv.** { *; }
-keep class com.element.** { *; }
