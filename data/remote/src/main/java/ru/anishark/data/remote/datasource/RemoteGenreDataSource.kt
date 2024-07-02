package ru.anishark.data.remote.datasource

import io.reactivex.rxjava3.core.Single
import ru.anishark.data.remote.api.GenreService
import ru.anishark.data.remote.mapper.RemoteGenreMapper
import ru.anishark.domain.model.AnimeGenreModel

class RemoteGenreDataSource(
    private val genreService: GenreService,
    private val remoteGenreMapper: RemoteGenreMapper,
) {
    fun getGenres(): Single<List<AnimeGenreModel>> =
        genreService
            .getAnimeGenres()
            .map { it.data.map(remoteGenreMapper::toDomainModel) }
}
