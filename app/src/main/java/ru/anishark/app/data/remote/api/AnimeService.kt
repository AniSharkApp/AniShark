package ru.anishark.app.data.remote.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap
import ru.anishark.app.data.remote.dto.response.SearchAnimeDTO

interface AnimeService {
    @GET("anime")
    fun getAnimeListUsingParameters(
        @QueryMap queryParams: Map<String, String>,
    ): Single<SearchAnimeDTO>
}
