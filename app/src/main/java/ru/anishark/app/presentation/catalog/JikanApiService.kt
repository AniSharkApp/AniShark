package ru.anishark.app.presentation.catalog

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface JikanApiService {
    @GET("anime")
    fun getAnimeListUsingAllParameters(
        @Query("genres") genres: String,
        @Query("type") type: String?,
        @Query("rating") rating: String?
    ): Single<AnimeResponse>
    @GET("anime")
    fun getAnimeListUsingGenresType(
        @Query("genres") genres: String,
        @Query("type") type: String?,
    ): Single<AnimeResponse>
    @GET("anime")
    fun getAnimeListUsingGenresRating(
        @Query("genres") genres: String,
        @Query("rating") rating: String,
    ): Single<AnimeResponse>
    @GET("anime")
    fun getAnimeListUsingTypeRating(
        @Query("type") type: String?,
        @Query("rating") rating: String?
    ): Single<AnimeResponse>
    @GET("anime")
    fun getAnimeListUsingGenres(
        @Query("genres") genres: String,
    ): Single<AnimeResponse>
    @GET("anime")
    fun getAnimeListUsingRating(
        @Query("rating") rating: String?
    ): Single<AnimeResponse>
    @GET("anime")
    fun getAnimeListUsingType(
        @Query("type") type: String?
    ): Single<AnimeResponse>
}