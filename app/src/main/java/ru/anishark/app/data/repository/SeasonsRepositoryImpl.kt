package ru.anishark.app.data.repository

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.datasource.SeasonsDataSource
import ru.anishark.app.data.di.annotation.Remote
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.repository.SeasonsRepository
import javax.inject.Inject

class SeasonsRepositoryImpl @Inject constructor(
    @Remote private val remoteSeasonsDataSource: SeasonsDataSource
): SeasonsRepository {
    override fun getCurrentSeasonAnime(): Single<List<AnimeModel>> {
        return remoteSeasonsDataSource.getSeasonsNowAnime()
    }
}