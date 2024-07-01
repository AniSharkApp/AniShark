package ru.anishark.domain.repository

import io.reactivex.rxjava3.core.Single
import ru.anishark.domain.model.AnimeGenreModel

interface GenresRepository {
    fun getAllGenres(): Single<List<AnimeGenreModel>>
}
