package ru.anishark.data.remote.datasource

import ru.anishark.data.remote.api.RecommendationsService
import javax.inject.Inject

class RemoteRecommendationsDataSource @Inject constructor(
    private val recommendationsService: RecommendationsService,
)
