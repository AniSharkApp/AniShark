package ru.anishark.app.presentation.catalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.anishark.app.presentation.catalog.JikanApiService
import ru.anishark.app.presentation.catalog.recycler.AnimeModelForCatalog
import javax.inject.Inject
