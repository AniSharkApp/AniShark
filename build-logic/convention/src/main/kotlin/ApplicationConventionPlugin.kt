import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import ru.anishark.configureKotlinAndroid
import ru.anishark.libs

class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.kapt")
                apply("com.google.dagger.hilt.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                buildFeatures {
                    viewBinding = true
                }
                compileSdk = 34
                defaultConfig.targetSdk = 34
                defaultConfig.minSdk = 26
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                @Suppress("UnstableApiUsage")
                testOptions.animationsDisabled = true
            }

            dependencies {
                add("implementation", libs.findLibrary("dagger-hiltAndroid").get())
                add("kapt", libs.findLibrary("dagger-hiltAndroidCompiler").get())
                add("implementation", libs.findLibrary("rxandroid").get())
                add("implementation", libs.findLibrary("rxkotlin").get())
            }
        }
    }
}
