package ru.anishark.app.presentation.filter.receiveGenreItemsFromApi

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GenreApi {
    @GET("genres/anime")
    fun getAnimeGenres(): Single<GenreResponse>
}