package ru.anishark.app.presentation.bookmark.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.anishark.app.domain.usecase.GetAllBookmarksCatalogUseCase
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val bookmarksUseCase: GetAllBookmarksCatalogUseCase
): ViewModel() {



    override fun onCleared() {
        super.onCleared()
    }
}