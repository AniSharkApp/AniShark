package ru.anishark.app.presentation.catalog.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.anishark.domain.model.AnimeGenreModel
import ru.anishark.domain.model.AnimeModel
import ru.anishark.domain.model.AnimeRatingModel
import ru.anishark.domain.model.AnimeTypeModel
import ru.anishark.domain.usecase.GetAnimeSearchUseCase
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getAnimeSearchUseCase: GetAnimeSearchUseCase,
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _searchResults = BehaviorSubject.create<List<AnimeModel>>()
    val searchResults
        get() = _searchResults.hide()

    fun searchAnime(
        ratings: List<AnimeRatingModel>,
        genres: List<AnimeGenreModel>,
        type: List<AnimeTypeModel>,
    ) {
        compositeDisposable +=
            getAnimeSearchUseCase(ratings, genres, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _searchResults.onNext(it)
                    },
                    {
                        _searchResults.onError(it)
                    },
                )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
