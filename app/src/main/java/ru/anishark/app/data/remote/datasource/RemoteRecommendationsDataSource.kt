package ru.anishark.app.data.remote.datasource

import ru.anishark.app.data.remote.api.RecommendationsService
import javax.inject.Inject

class RemoteRecommendationsDataSource @Inject constructor(
    private val recommendationsService: RecommendationsService,
)
