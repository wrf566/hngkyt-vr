# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/wrf/Library/Android/sdk/tools/proguard/proguard-android.txt
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
#-------通用的混淆策略--------
#指定压缩级别
-optimizationpasses 5

#不跳过非公共的库的类成员
-dontskipnonpubliclibraryclassmembers

#混淆时采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#把混淆类中的方法名也混淆了
-useuniqueclassmembernames

#优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification

#将文件来源重命名为“SourceFile”字符串
-renamesourcefileattribute SourceFile
#保留行号
-keepattributes SourceFile,LineNumberTable

#保持所有实现 Serializable 接口的类成员
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#Fragment不需要在AndroidManifest.xml中注册，需要额外保护下
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment

#----------------自定义----------------
#Json解析所以要保持model类
-keep class com.hngkyt.vr.model.** { *; }

# 保持测试相关的代码
-dontnote junit.framework.**
-dontnote junit.runner.**
-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**

#-------Google VR视频播放的SDK-----------

# Don't obfuscate any NDK/SDK code. This makes the debugging of stack traces in
# in release builds easier.
-keepnames class com.google.vr.ndk.** { *; }
-keepnames class com.google.vr.sdk.** { *; }

# These are part of the Java <-> native interfaces for GVR.
-keepclasseswithmembernames,includedescriptorclasses class com.google.vr.** {
    native <methods>;
}

-keep class com.google.vr.cardboard.UsedByNative
-keep @com.google.vr.cardboard.UsedByNative class *
-keepclassmembers class * {
    @com.google.vr.cardboard.UsedByNative *;
}

-keep class com.google.vr.cardboard.annotations.UsedByNative
-keep @com.google.vr.cardboard.annotations.UsedByNative class *
-keepclassmembers class * {
    @com.google.vr.cardboard.annotations.UsedByNative *;
}

-keep class com.google.vr.cardboard.annotations.UsedByReflection
-keep @com.google.vr.cardboard.annotations.UsedByReflection class *
-keepclassmembers class * {
    @com.google.vr.cardboard.annotations.UsedByReflection *;
}

#--------Glide-----------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#下面这一句是要建立glide Module才使用，暂时项目中未用到
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule


#--------retrofit2-----------
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
#okio官网推荐这样做
-dontwarn okio.**



