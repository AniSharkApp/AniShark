package ru.anishark.data.remote.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.anishark.data.remote.RemoteConstants.SEASONS_NOW_PATH
import ru.anishark.data.remote.dto.response.SeasonsNowDTO

interface SeasonsService {
    @GET(SEASONS_NOW_PATH)
    fun getSeasonsNow(): Single<SeasonsNowDTO>
}
