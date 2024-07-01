package ru.anishark.app.domain.repository

import io.reactivex.rxjava3.core.Observable
import ru.anishark.app.domain.model.AnimeModel

interface TopRepository {
    fun getTopAnime(): Observable<List<AnimeModel>>
}
