package ru.anishark.app.data.remote.api

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import ru.anishark.app.data.remote.RemoteConstants.SEASONS_NOW_PATH
import ru.anishark.app.data.remote.dto.response.SeasonsNowDTO

interface SeasonsService {
    @GET(SEASONS_NOW_PATH)
    fun getSeasonsNow(): Observable<SeasonsNowDTO>
}