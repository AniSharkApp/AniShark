package ru.anishark.app.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.remote.api.GenreService
import ru.anishark.app.domain.model.AnimeGenreModel
import javax.inject.Inject

class RemoteGenreDataSource @Inject constructor(
    private val genreService: GenreService,
) {
    fun getGenres(): Single<List<AnimeGenreModel>> =
        genreService
            .getAnimeGenres()
            .map {
                it.data.map {
                    AnimeGenreModel(
                        malId = it.malId,
                        name = it.name,
                        url = it.url,
                        count = it.count,
                    )
                }
            }
}
