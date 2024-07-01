import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import ru.anishark.configureKotlinAndroid
import ru.anishark.libs

class LibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.kapt")
                apply("com.google.dagger.hilt.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
                defaultConfig.minSdk = 26
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
