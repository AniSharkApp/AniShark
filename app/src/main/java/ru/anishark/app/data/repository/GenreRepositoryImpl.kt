package ru.anishark.app.data.repository

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.remote.datasource.RemoteGenreDataSource
import ru.anishark.app.domain.model.AnimeGenreModel
import ru.anishark.app.domain.repository.GenresRepository
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val remoteGenreDataSource: RemoteGenreDataSource,
) : GenresRepository {
    override fun getAllGenres(): Single<List<AnimeGenreModel>> = remoteGenreDataSource.getGenres()
}
