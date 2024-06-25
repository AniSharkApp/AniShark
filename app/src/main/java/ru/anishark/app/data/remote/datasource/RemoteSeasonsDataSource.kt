package ru.anishark.app.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.remote.api.SeasonsService
import ru.anishark.app.domain.model.AnimeModel
import javax.inject.Inject

class RemoteSeasonsDataSource @Inject constructor(
    private val remoteSeasonsService: SeasonsService,
) {
    fun getSeasonsNowAnime(): Single<List<AnimeModel>> =
        remoteSeasonsService
            .getSeasonsNow()
            .map {
                // TODO закинуть логику маппинга в Mapper
                it.data.map {
                    AnimeModel(
                        title =
                            it.titles
                                .first()
                                .title,
                        synopsis = it.synopsis ?: "",
                        year = it.year ?: 0,
                        episodes = it.episodes,
                        imageUrl = it.images.jpeg.imageUrl ?: TODO(),
                        score = it.score,
                        malId = it.malId,
                    )
                }
            }
}
