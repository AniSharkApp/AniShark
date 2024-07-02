package ru.anishark.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ru.anishark.data.remote.api.SeasonsService
import ru.anishark.data.remote.mapper.RemoteAnimeMapper
import ru.anishark.domain.model.AnimeModel

class RemoteSeasonsDataSource(
    private val remoteSeasonsService: SeasonsService,
    private val remoteAnimeMapper: RemoteAnimeMapper,
) {
    fun getSeasonsNowAnime(): Single<List<AnimeModel>> =
        remoteSeasonsService
            .getSeasonsNow()
            .map {
                it.data.map(remoteAnimeMapper::toDomainModel)
            }
}
