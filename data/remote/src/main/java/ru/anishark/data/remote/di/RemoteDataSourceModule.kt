package ru.anishark.data.remote.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import ru.anishark.data.remote.RemoteConstants.BASE_URL
import ru.anishark.data.remote.api.AnimeService
import ru.anishark.data.remote.api.GenreService
import ru.anishark.data.remote.api.RecommendationsService
import ru.anishark.data.remote.api.SeasonsService
import ru.anishark.data.remote.api.TopService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {
    @Provides
    @Singleton
    fun provideJson(): Json =
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = false
        }

    @Provides
    @Singleton
    fun createRxJavaCallAdapterFactory(): RxJava3CallAdapterFactory = RxJava3CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideJsonConverterFactory(json: Json): Converter.Factory = json.asConverterFactory("application/json".toMediaType())

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor(
                    object : HttpLoggingInterceptor.Logger {
                        override fun log(message: String) {
                            Log.d("OkHttp", message)
                        }
                    },
                ).apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                },
            ).build()

    @Provides
    @Singleton
    fun provideRetrofit(
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        jsonConverterFactory: Converter.Factory,
        okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(jsonConverterFactory)
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .build()

    @Provides
    @Singleton
    fun provideAnimeService(retrofit: Retrofit): AnimeService = retrofit.create(AnimeService::class.java)

    @Provides
    @Singleton
    fun provideGenreService(retrofit: Retrofit): GenreService = retrofit.create(GenreService::class.java)

    @Provides
    @Singleton
    fun provideRecommendationService(retrofit: Retrofit): RecommendationsService = retrofit.create(RecommendationsService::class.java)

    @Provides
    @Singleton
    fun provideSeasonsService(retrofit: Retrofit): SeasonsService = retrofit.create(SeasonsService::class.java)

    @Provides
    @Singleton
    fun provideTopService(retrofit: Retrofit): TopService = retrofit.create(TopService::class.java)
}
