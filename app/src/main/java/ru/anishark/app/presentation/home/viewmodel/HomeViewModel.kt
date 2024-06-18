package ru.anishark.app.presentation.home.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.usecase.LoadActualHomeUseCase
import ru.anishark.app.domain.usecase.LoadTopsHomeUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val loadTopsHomeUseCase: LoadTopsHomeUseCase,
    private val loadActualHomeUseCase: LoadActualHomeUseCase,
) : ViewModel() {
    // TODO скомбайнить состояния, подумать над тем как создать BehaviorSubject реемитом
    private val _topsState = BehaviorSubject.fromSingle(loadTopsHomeUseCase())
    val topsState: Observable<List<AnimeModel>> get() = _topsState.hide()

    private val _actualState = BehaviorSubject.fromSingle(loadActualHomeUseCase())
    val actualState: Observable<List<AnimeModel>> get() = _actualState.hide()
}
