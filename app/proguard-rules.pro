# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Software\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}



-keep class retrofit2.** { *; }
-keep class retrofit2.http.** { *; }
-keep class retrofit2.client.** { *; }

-keep interface retrofit2.** { *;}
-keep interface com.squareup.** { *; }
-dontwarn retrofit2.**
-dontwarn okio.**


# EventBus ====================
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}
