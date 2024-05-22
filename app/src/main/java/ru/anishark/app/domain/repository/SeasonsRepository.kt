package ru.anishark.app.domain.repository

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.domain.model.AnimeModel

interface SeasonsRepository {
    fun getCurrentSeasonAnime(): Single<List<AnimeModel>>
}