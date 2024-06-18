package ru.anishark.app.presentation.anime.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.anishark.app.domain.usecase.GetOneBookmarkCatalogUseCase
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val oneBookmarkUseCase: GetOneBookmarkCatalogUseCase,
) : ViewModel() {
    fun getBookmark(animeId: Int) = oneBookmarkUseCase(animeId)
}
