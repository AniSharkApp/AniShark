package ru.anishark.app.common.ui

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import io.reactivex.rxjava3.disposables.Disposable

fun Disposable.disposeOnDestroy(lifecycle: Lifecycle) {
    lifecycle.addObserver(
        object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                this@disposeOnDestroy.dispose()
                lifecycle.removeObserver(this)
            }
        }
    )
}