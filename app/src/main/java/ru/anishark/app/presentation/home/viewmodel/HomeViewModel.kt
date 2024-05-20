package ru.anishark.app.presentation.home.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.anishark.app.domain.usecase.LoadActualHomeUseCase
import ru.anishark.app.domain.usecase.LoadTopsHomeUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val loadTopsHomeUseCase: LoadTopsHomeUseCase,
    private val loadActualHomeUseCase: LoadActualHomeUseCase
) : ViewModel() {
    val topsState = BehaviorSubject.fromSingle(loadTopsHomeUseCase())
    val actualState = BehaviorSubject.fromSingle(loadActualHomeUseCase())
}