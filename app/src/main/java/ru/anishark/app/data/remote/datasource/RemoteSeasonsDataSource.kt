package ru.anishark.app.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.remote.api.SeasonsService
import ru.anishark.app.data.remote.mapper.RemoteAnimeMapper
import ru.anishark.app.domain.model.AnimeModel
import javax.inject.Inject

class RemoteSeasonsDataSource @Inject constructor(
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
