package ru.anishark.app.data.remote

import ru.anishark.app.data.remote.api.AnimeService
import ru.anishark.app.data.remote.api.RecommendationsService
import ru.anishark.app.data.remote.api.SeasonsService
import ru.anishark.app.data.remote.api.TopService

class RemoteDataSource(
    private val animeService: AnimeService,
    private val seasonsService: SeasonsService,
    private val recommendationsService: RecommendationsService,
    private val topService: TopService
) {

}