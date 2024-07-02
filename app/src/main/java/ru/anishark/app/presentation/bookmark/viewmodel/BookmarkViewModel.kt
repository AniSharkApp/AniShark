package ru.anishark.app.presentation.bookmark.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.anishark.app.presentation.bookmark.recycler.BookmarksState
import ru.anishark.domain.model.AnimeModel
import ru.anishark.domain.usecase.AddBookmarkCatalogUseCase
import ru.anishark.domain.usecase.GetAllBookmarksUseCase
import ru.anishark.domain.usecase.GetOneBookmarkUseCase
import ru.anishark.domain.usecase.RemoveBookmarkUseCase
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val allBookmarksUseCase: GetAllBookmarksUseCase,
    private val oneBookmarkUseCase: GetOneBookmarkUseCase,
    private val insertBookmarkUseCase: AddBookmarkCatalogUseCase,
    private val deleteBookmarkUseCase: RemoveBookmarkUseCase,
) : ViewModel() {
    private val _bookmarksState: BehaviorSubject<BookmarksState> = BehaviorSubject.createDefault(BookmarksState.Loading)
    val bookmarksState get() = _bookmarksState.hide()

    val compositeDisposable = CompositeDisposable()

    fun loadBookmarks() {
        compositeDisposable +=
            allBookmarksUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        if (it.isNotEmpty()) {
                            _bookmarksState.onNext(BookmarksState.Content(it))
                        } else {
                            _bookmarksState.onNext(BookmarksState.EmptyContent)
                        }
                    },
                    {
                        _bookmarksState.onNext(BookmarksState.Error(it.message))
                    },
                )
    }

    fun insertBookmark(anime: AnimeModel) = insertBookmarkUseCase(anime)

    fun deleteBookmark(animeId: Int) = deleteBookmarkUseCase(animeId)

    fun getBookmark(animeId: Int) = oneBookmarkUseCase(animeId)

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
