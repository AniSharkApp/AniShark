package ru.anishark.app.presentation.anime.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.anishark.domain.model.AnimeModel
import ru.anishark.domain.model.BookmarkModel
import ru.anishark.domain.usecase.DeleteBookmarkCatalogUseCase
import ru.anishark.domain.usecase.GetOnClickAnimeUseCase
import ru.anishark.domain.usecase.GetOneBookmarkCatalogUseCase
import ru.anishark.domain.usecase.InsertBookmarkAnimeUseCase
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val oneBookmarkUseCase: GetOneBookmarkCatalogUseCase,
    private val insertBookmarkUseCase: InsertBookmarkAnimeUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkCatalogUseCase,
    private val getOnClickAnimeUseCase: GetOnClickAnimeUseCase,
) : ViewModel() {
    val compositeDisposable = CompositeDisposable()

    private val _currentAnime = BehaviorSubject.create<AnimeModel>()
    val currentAnime
        get() = _currentAnime.hide()

    var isAnimeInBookmark = false

    fun loadData(malId: Int) {
        compositeDisposable +=
            getOnClickAnimeUseCase(malId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _currentAnime.onNext(it)
                    },
                    {
                        _currentAnime.onError(it)
                    },
                )

        compositeDisposable +=
            getBookmark(malId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        isAnimeInBookmark = it.let { true } ?: false
                    },
                    {
                        Log.d("MyLog", it.message.toString())
                    },
                )
    }

    fun getBookmark(animeId: Int) = oneBookmarkUseCase(animeId)

    fun insertBookmark(anime: BookmarkModel) = insertBookmarkUseCase(anime)

    fun deleteBookmark(animeId: Int) = deleteBookmarkUseCase(animeId)

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
