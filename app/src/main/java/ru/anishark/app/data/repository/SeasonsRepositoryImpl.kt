package ru.anishark.app.data.repository

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.remote.datasource.RemoteSeasonsDataSource
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.repository.SeasonsRepository
import javax.inject.Inject

class SeasonsRepositoryImpl @Inject constructor(
    private val remoteSeasonsDataSource: RemoteSeasonsDataSource,
) : SeasonsRepository {
    override fun getCurrentSeasonAnime(): Single<List<AnimeModel>> = remoteSeasonsDataSource.getSeasonsNowAnime()
}
