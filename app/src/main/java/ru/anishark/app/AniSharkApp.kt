package ru.anishark.app

import android.util.Log
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins

@HiltAndroidApp
class AniSharkApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { e ->
            if (e is UndeliverableException) {
                Log.e("APP", "Got RxJava's UndeliverableException with msg: ${e.message}")
            } else {
                Thread.currentThread().also { thread ->
                    thread.uncaughtExceptionHandler?.uncaughtException(thread, e)
                }
            }
        }
    }
}
