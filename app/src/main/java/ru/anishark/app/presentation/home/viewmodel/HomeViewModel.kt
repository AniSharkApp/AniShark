package ru.anishark.app.presentation.home.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.usecase.LoadActualHomeUseCase
import ru.anishark.app.domain.usecase.LoadTopsHomeUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val loadTopsHomeUseCase: LoadTopsHomeUseCase,
    private val loadActualHomeUseCase: LoadActualHomeUseCase,
) : ViewModel() {
    val compositeDisposable = CompositeDisposable()

    // TODO скомбайнить состояния
    private val _topsState = BehaviorSubject.create<List<AnimeModel>>()
    val topsState
        get() = _topsState.hide()

    private val _actualState = BehaviorSubject.create<List<AnimeModel>>()
    val actualState
        get() = _actualState.hide()

    fun loadAllData() {
        compositeDisposable +=
            loadTopsHomeUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _topsState.onNext(it)
                    },
                    {
                        _topsState.onError(it)
                    },
                )
        compositeDisposable +=
            loadActualHomeUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _actualState.onNext(it)
                    },
                    {
                        _actualState.onError(it)
                    },
                )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
