package ru.anishark.app.presentation.bookmark.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.usecase.DeleteBookmarkCatalogUseCase
import ru.anishark.app.domain.usecase.GetAllBookmarksCatalogUseCase
import ru.anishark.app.domain.usecase.GetOneBookmarkCatalogUseCase
import ru.anishark.app.domain.usecase.InsertBookmarkCatalogUseCase
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val allBookmarksUseCase: GetAllBookmarksCatalogUseCase,
    private val oneBookmarkUseCase: GetOneBookmarkCatalogUseCase,
    private val insertBookmarkUseCase: InsertBookmarkCatalogUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkCatalogUseCase
): ViewModel() {
    private val _bookmarksState = BehaviorSubject.fromFuture(allBookmarksUseCase().toFuture())
    val bookmarks get() = _bookmarksState.hide()

    fun insertBookmark(anime: AnimeModel) = insertBookmarkUseCase(anime)

    fun deleteBookmark(anime: BookmarkAnimeEntity) = deleteBookmarkUseCase(anime)

    fun getBookmark(animeId: Int) = oneBookmarkUseCase(animeId).toFuture().get()

}