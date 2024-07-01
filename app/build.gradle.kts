plugins {
    id("anishark.android.application")
    alias(libs.plugins.serialization)
}

hilt {
    enableAggregatingTask = true
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = "ru.anishark.app"

    defaultConfig {
        applicationId = "ru.anishark.app"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data:repository"))

    // Coil
    implementation(libs.coil)

    implementation(libs.androidx.fragment.ktx)

    // MultiDex - for Dagger
    implementation(libs.androidx.multidex)

    // Core Splashscreen API
    implementation(libs.androidx.core.splashscreen)

    // Datastore
    implementation(libs.androidx.datastore.preferences.rxjava3)

    implementation(libs.dagger.hiltAndroid)
    kapt(libs.dagger.hiltAndroidCompiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
