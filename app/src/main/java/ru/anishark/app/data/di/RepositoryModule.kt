package ru.anishark.app.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.anishark.app.data.repository.AnimeRepositoryImpl
import ru.anishark.app.data.repository.RecommendationsRepositoryImpl
import ru.anishark.app.data.repository.SeasonsRepositoryImpl
import ru.anishark.app.data.repository.TopRepositoryImpl
import ru.anishark.app.domain.repository.AnimeRepository
import ru.anishark.app.domain.repository.RecommendationsRepository
import ru.anishark.app.domain.repository.SeasonsRepository
import ru.anishark.app.domain.repository.TopRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAnimeRepository(
        impl: AnimeRepositoryImpl
    ): AnimeRepository

    @Binds
    @Singleton
    abstract fun bindRecommendationsRepository(
        impl: RecommendationsRepositoryImpl
    ): RecommendationsRepository

    @Binds
    @Singleton
    abstract fun bindSeasonsRepository(
        impl: SeasonsRepositoryImpl
    ): SeasonsRepository

    @Binds
    @Singleton
    abstract fun bindTopRepository(
        impl: TopRepositoryImpl
    ): TopRepository
}