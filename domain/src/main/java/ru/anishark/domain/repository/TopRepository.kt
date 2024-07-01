package ru.anishark.domain.repository

import io.reactivex.rxjava3.core.Observable
import ru.anishark.domain.model.AnimeModel

interface TopRepository {
    fun getTopAnime(): Observable<List<AnimeModel>>
}
