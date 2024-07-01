package ru.anishark.app.data.repository

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.remote.datasource.RemoteTopDataSource
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.repository.TopRepository
import javax.inject.Inject

class TopRepositoryImpl @Inject constructor(
    private val remoteTopDataSource: RemoteTopDataSource,
) : TopRepository {
    override fun getTopAnime(): Single<List<AnimeModel>> = remoteTopDataSource.getTopAnime()
}
