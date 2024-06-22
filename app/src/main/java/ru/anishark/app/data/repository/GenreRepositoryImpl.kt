package ru.anishark.app.data.repository

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.data.datasource.GenreDataSource
import ru.anishark.app.data.di.annotation.Remote
import ru.anishark.app.domain.model.AnimeGenreModel
import ru.anishark.app.domain.repository.GenresRepository
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    @Remote private val remoteGenreDataSourceImpl: GenreDataSource,
) : GenresRepository {
    override fun getAllGenres(): Single<List<AnimeGenreModel>> = remoteGenreDataSourceImpl.getGenres()
}
