package ru.anishark.app.data.remote.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ru.anishark.app.data.remote.dto.response.AnimeFullDTO
import ru.anishark.app.data.remote.dto.response.SearchAnimeDTO

interface AnimeService {
    @GET("anime")
    fun getAnimeListUsingParameters(
        @QueryMap queryParams: Map<String, String>,
    ): Single<SearchAnimeDTO>

    @GET("anime/{id}/full")
    fun getAnime(@Path("id") malId: Int): Single<AnimeFullDTO>
}
