package ru.anishark.app.presentation.anime.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.anishark.app.domain.model.BookmarkModel
import ru.anishark.app.domain.usecase.DeleteBookmarkCatalogUseCase
import ru.anishark.app.domain.usecase.GetOneBookmarkCatalogUseCase
import ru.anishark.app.domain.usecase.InsertBookmarkAnimeUseCase
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val oneBookmarkUseCase: GetOneBookmarkCatalogUseCase,
    private val insertBookmarkUseCase: InsertBookmarkAnimeUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkCatalogUseCase,
) : ViewModel() {
    fun getBookmark(animeId: Int) = oneBookmarkUseCase(animeId)

    fun insertBookmark(anime: BookmarkModel) = insertBookmarkUseCase(anime)

    fun deleteBookmark(animeId: Int) = deleteBookmarkUseCase(animeId)
}
