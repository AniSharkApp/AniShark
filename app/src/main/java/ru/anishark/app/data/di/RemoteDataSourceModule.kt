package ru.anishark.app.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import ru.anishark.app.data.datasource.AnimeDataSource
import ru.anishark.app.data.datasource.RecommendationsDataSource
import ru.anishark.app.data.datasource.SeasonsDataSource
import ru.anishark.app.data.datasource.TopDataSource
import ru.anishark.app.data.di.annotation.Remote
import ru.anishark.app.data.remote.RemoteConstants.BASE_URL
import ru.anishark.app.data.remote.api.AnimeService
import ru.anishark.app.data.remote.api.RecommendationsService
import ru.anishark.app.data.remote.api.SeasonsService
import ru.anishark.app.data.remote.api.TopService
import ru.anishark.app.data.remote.datasource.RemoteAnimeDataSourceImpl
import ru.anishark.app.data.remote.datasource.RemoteRecommendationsDataSourceImpl
import ru.anishark.app.data.remote.datasource.RemoteSeasonsDataSourceImpl
import ru.anishark.app.data.remote.datasource.RemoteTopDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {
    @Provides
    @Singleton
    fun provideJson(): Json =
        Json {
            ignoreUnknownKeys = true
        }

    @Provides
    @Singleton
    fun createRxJavaCallAdapterFactory(): RxJava3CallAdapterFactory = RxJava3CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideJsonConverterFactory(json: Json): Converter.Factory = json.asConverterFactory("application/json".toMediaType())

    @Provides
    @Singleton
    fun provideRetrofit(
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        jsonConverterFactory: Converter.Factory,
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonConverterFactory)
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .build()

    @Provides
    @Singleton
    fun provideAnimeService(retrofit: Retrofit): AnimeService = retrofit.create(AnimeService::class.java)

    @Provides
    @Singleton
    fun provideRecommendationService(retrofit: Retrofit): RecommendationsService = retrofit.create(RecommendationsService::class.java)

    @Provides
    @Singleton
    fun provideSeasonsService(retrofit: Retrofit): SeasonsService = retrofit.create(SeasonsService::class.java)

    @Provides
    @Singleton
    fun provideTopService(retrofit: Retrofit): TopService = retrofit.create(TopService::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class BindsModule {
        @Binds
        @Singleton
        @Remote
        abstract fun bindRemoteAnimeDataSource(impl: RemoteAnimeDataSourceImpl): AnimeDataSource

        @Binds
        @Singleton
        @Remote
        abstract fun bindRemoteRecommendationsDataSource(impl: RemoteRecommendationsDataSourceImpl): RecommendationsDataSource

        @Binds
        @Singleton
        @Remote
        abstract fun bindRemoteSeasonsDataSource(impl: RemoteSeasonsDataSourceImpl): SeasonsDataSource

        @Binds
        @Singleton
        @Remote
        abstract fun bindRemoteTopDataSource(impl: RemoteTopDataSourceImpl): TopDataSource
    }
}
