import java.util.regex.Pattern.compile


plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.codingbook"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
    }
    defaultConfig {
        applicationId = "com.example.codingbook"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_9
        targetCompatibility = JavaVersion.VERSION_1_9
    }
    buildToolsVersion = "34.0.0"
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("androidx.vectordrawable:vectordrawable:1.1.0")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-firestore:24.10.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    testImplementation("junit:junit:4.13.2")
    implementation("com.google.firebase:firebase-bom:32.7.0")
    implementation ("com.google.gms:google-services:3.1.2")
    implementation ("com.github.ertugrulkaragoz:SuperBottomBar:0.4")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.61")
    implementation ("com.google.android.gms:play-services-auth:20.7.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.61")
    implementation ("com.airbnb.android:lottie:3.4.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.etebarian:meow-bottom-navigation:1.3.1")
    implementation ("com.github.10clouds:FluidBottomNavigation-android:1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


}