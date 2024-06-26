package ru.anishark.app.presentation.bookmark.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.usecase.DeleteBookmarkCatalogUseCase
import ru.anishark.app.domain.usecase.GetAllBookmarksCatalogUseCase
import ru.anishark.app.domain.usecase.GetOneBookmarkCatalogUseCase
import ru.anishark.app.domain.usecase.InsertBookmarkCatalogUseCase
import ru.anishark.app.presentation.bookmark.recycler.BookmarksState
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val allBookmarksUseCase: GetAllBookmarksCatalogUseCase,
    private val oneBookmarkUseCase: GetOneBookmarkCatalogUseCase,
    private val insertBookmarkUseCase: InsertBookmarkCatalogUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkCatalogUseCase,
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
