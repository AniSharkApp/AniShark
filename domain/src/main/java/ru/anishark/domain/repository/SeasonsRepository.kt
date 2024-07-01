package ru.anishark.domain.repository

import io.reactivex.rxjava3.core.Observable
import ru.anishark.domain.model.AnimeModel

interface SeasonsRepository {
    fun getCurrentSeasonAnime(): Observable<List<AnimeModel>>
}
