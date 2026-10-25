# DevxyzShield v2.0 ProGuard / R8 Rules

# Keep kotlinx.serialization classes
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}
-keep,includedescriptorclasses class com.jepongdevxyz.devxyzshield.**$$serializer { *; }
-keepclassmembers class com.jepongdevxyz.devxyzshield.** {
    *** Companion;
}
-keepclasseswithmembers class com.jepongdevxyz.devxyzshield.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# Keep Room entities & DAOs
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Keep Google Play Billing
-keep class com.android.vending.billing.**

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**
