package ru.anishark.app.presentation.main.bus

import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class NavigationEventBus @Inject constructor() {
    private val _subject = PublishSubject.create<Int>()
    val subject
        get() = _subject.hide()

    fun changeTo(pageIdx: Int) {
        _subject.onNext(pageIdx)
    }
}
