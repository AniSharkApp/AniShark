package ru.anishark.app.presentation.filter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.anishark.domain.model.AnimeGenreModel
import ru.anishark.domain.usecase.GetFilterGenresUseCase
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val getFilterGenresUseCase: GetFilterGenresUseCase,
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _genres = BehaviorSubject.create<List<AnimeGenreModel>>()
    val genres
        get() = _genres.hide()

    fun loadGenres() {
        compositeDisposable +=
            getFilterGenresUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.i("FILTER", "Loaded $it")
                        _genres.onNext(it)
                    },
                    {
                        Log.i("FILTER", "Errored $it")
                        _genres.onError(it)
                    },
                )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
