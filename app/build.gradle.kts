plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.safe.args)
}

android {
    namespace = "com.example.yourmeal"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.yourmeal"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.credentials)
    implementation(libs.credentials.play.services.auth)
    implementation(libs.googleid)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation (libs.lottie)

    implementation(platform(libs.firebase.bom))
    implementation(libs.google.firebase.auth)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)
    implementation(libs.glide)

    implementation (libs.room.runtime)
    annotationProcessor (libs.room.compiler)

    implementation(libs.android.youtube.player)

    implementation (libs.adapter.rxjava3)
    implementation (libs.room.rxjava3)

    implementation (libs.rxandroid)
    implementation (libs.rxjava)


    implementation (libs.play.services.auth)

    implementation (libs.circleimageview)


}