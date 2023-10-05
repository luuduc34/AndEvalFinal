// Top-level build file where you can add configuration options common to all sub-projects/modules.

//nav
buildscript{
    repositories{
        google()
    }
    dependencies{
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.0")
    }
}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false

    //room
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
}