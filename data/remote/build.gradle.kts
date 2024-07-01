plugins {
    id("anishark.android.library")
    alias(libs.plugins.serialization)
}

hilt {
    enableAggregatingTask = true
}

android {
    namespace = "ru.anishark.data.remote"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
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

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinxConverter)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.adapter.rxjava3)
    // OkHttp
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
