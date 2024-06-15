package ru.anishark.app.data.remote.datasource

import ru.anishark.app.data.datasource.RecommendationsDataSource
import ru.anishark.app.data.remote.api.RecommendationsService
import javax.inject.Inject

class RemoteRecommendationsDataSourceImpl @Inject constructor(
    private val recommendationsService: RecommendationsService,
) : RecommendationsDataSource
