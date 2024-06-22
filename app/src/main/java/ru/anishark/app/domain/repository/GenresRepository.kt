package ru.anishark.app.domain.repository

import io.reactivex.rxjava3.core.Single
import ru.anishark.app.domain.model.AnimeGenreModel

interface GenresRepository {
    fun getAllGenres(): Single<List<AnimeGenreModel>>
}
