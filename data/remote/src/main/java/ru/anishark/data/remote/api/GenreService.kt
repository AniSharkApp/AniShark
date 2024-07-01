package ru.anishark.data.remote.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.anishark.data.remote.dto.response.GenresDTO

interface GenreService {
    @GET("genres/anime")
    fun getAnimeGenres(): Single<GenresDTO>
}
