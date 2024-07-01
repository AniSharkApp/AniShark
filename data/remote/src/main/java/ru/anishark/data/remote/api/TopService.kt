package ru.anishark.data.remote.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.anishark.data.remote.RemoteConstants.TOP_ANIME_PATH
import ru.anishark.data.remote.dto.response.TopAnimeDTO

interface TopService {
    @GET(TOP_ANIME_PATH)
    fun getTopAnime(): Single<TopAnimeDTO>
}
