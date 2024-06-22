package ru.anishark.app.data.datasource

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.domain.model.AnimeGenreModel

interface GenreDataSource {
    fun getGenres(): Single<List<AnimeGenreModel>>
}
