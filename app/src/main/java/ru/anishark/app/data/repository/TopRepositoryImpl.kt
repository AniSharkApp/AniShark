package ru.anishark.app.data.repository

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.datasource.TopDataSource
import ru.anishark.app.data.di.annotation.Remote
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.repository.TopRepository
import javax.inject.Inject

class TopRepositoryImpl @Inject constructor(
    @Remote private val remoteTopDataSource: TopDataSource,
) : TopRepository {
    override fun getTopAnime(): Single<List<AnimeModel>> = remoteTopDataSource.getTopAnime()
}
