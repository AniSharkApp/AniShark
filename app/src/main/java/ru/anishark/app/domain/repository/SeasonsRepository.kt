package ru.anishark.app.domain.repository

import io.reactivex.rxjava3.core.Observable
import ru.anishark.app.domain.model.AnimeModel

interface SeasonsRepository {
    fun getCurrentSeasonAnime(): Observable<List<AnimeModel>>
}
