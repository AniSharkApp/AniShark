package ru.anishark.app.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.remote.api.TopService
import ru.anishark.app.data.datasource.TopDataSource
import ru.anishark.app.domain.model.AnimeModel
import javax.inject.Inject

class RemoteTopDataSourceImpl @Inject constructor(
    private val remoteTopService: TopService
): TopDataSource {
    override fun getTopAnime(): Single<List<AnimeModel>> {
        return remoteTopService.getTopAnime()
            .map {
                // TODO закинуть логику маппинга в Mapper
                it.data.map {
                    AnimeModel(
                        title = it.titles.first().title,
                        synopsis = it.synopsis,
                        year = it.year ?: 0,
                        episodes = it.episodes,
                        imageUrl = it.images.jpeg.imageUrl ?: TODO(),
                        score = it.score,
                        malId = it.malId
                    )
                }
            }
    }
}