package ru.anishark.app.data.remote.datasource

import ru.anishark.app.data.remote.api.AnimeService
import ru.anishark.app.data.datasource.AnimeDataSource
import javax.inject.Inject

class RemoteAnimeDataSourceImpl @Inject constructor(
    private val animeService: AnimeService
): AnimeDataSource {

}