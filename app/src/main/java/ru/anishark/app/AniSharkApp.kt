package ru.anishark.app

import android.util.Log
import androidx.multidex.MultiDexApplication
import coil.Coil
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins

@HiltAndroidApp
class AniSharkApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        val imageLoader =
            ImageLoader
                .Builder(this)
                .memoryCache {
                    MemoryCache
                        .Builder(this)
                        .maxSizePercent(0.25)
                        .build()
                }.diskCache {
                    DiskCache
                        .Builder()
                        .directory(this.cacheDir.resolve("image_cache"))
                        .maxSizePercent(0.05)
                        .build()
                }.build()
        Coil.setImageLoader(imageLoader)
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
