# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Applications/adt-bundle-mac-x86_64-20131030/sdk/tools/proguard/proguard-android.txt
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
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

-keep class org.bouncycastle.** { *; }
-dontwarn org.bouncycastle.**

-keep class com.getpebble.android.kit.** { *; }
-dontwarn com.getpebble.android.kit.**

-keep class com.google.common.** { *; }
-dontwarn com.google.common.**

-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**

-keep class info.gdgcatania.android.app.tictactoe.api.classes.** { *; }
-dontwarn info.gdgcatania.android.app.tictactoe.api.classes.**