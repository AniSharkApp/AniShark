package ru.anishark.app.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.remote.api.SeasonsService
import ru.anishark.app.data.datasource.SeasonsDataSource
import ru.anishark.app.domain.model.AnimeModel
import javax.inject.Inject

class RemoteSeasonsDataSourceImpl @Inject constructor(
    private val remoteSeasonsService: SeasonsService
): SeasonsDataSource {
    override fun getSeasonsNowAnime(): Single<List<AnimeModel>> {
        return remoteSeasonsService.getSeasonsNow()
            .map {
                // TODO закинуть логику маппинга в Mapper
                it.data.map {
                    AnimeModel(
                        title = it.titles.first().title,
                        synopsis = it.synopsis,
                        year = it.year ?: 0,
                        episodes = it.episodes ?: -666,
                        imageUrl = it.images.jpeg.imageUrl ?: TODO(),
                        score = it.score
                    )
                }
            }
    }
}