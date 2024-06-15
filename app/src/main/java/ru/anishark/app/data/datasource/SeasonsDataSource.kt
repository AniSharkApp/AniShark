package ru.anishark.app.data.datasource

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.domain.model.AnimeModel

interface SeasonsDataSource {
    fun getSeasonsNowAnime(): Single<List<AnimeModel>>
}
