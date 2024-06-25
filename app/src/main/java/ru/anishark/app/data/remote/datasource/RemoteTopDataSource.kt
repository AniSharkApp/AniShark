package ru.anishark.app.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.remote.api.TopService
import ru.anishark.app.data.remote.mapper.RemoteAnimeMapper
import ru.anishark.app.domain.model.AnimeModel
import javax.inject.Inject

class RemoteTopDataSource @Inject constructor(
    private val remoteTopService: TopService,
    private val remoteAnimeMapper: RemoteAnimeMapper,
) {
    fun getTopAnime(): Single<List<AnimeModel>> =
        remoteTopService
            .getTopAnime()
            .map {
                it.data.map(remoteAnimeMapper::toDomainModel)
            }
}
