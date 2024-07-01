package ru.anishark.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.anishark.data.repository.AnimeRepositoryImpl
import ru.anishark.data.repository.BookmarkRepositoryImpl
import ru.anishark.data.repository.GenreRepositoryImpl
import ru.anishark.data.repository.RecommendationsRepositoryImpl
import ru.anishark.data.repository.SeasonsRepositoryImpl
import ru.anishark.data.repository.TopRepositoryImpl
import ru.anishark.domain.repository.AnimeRepository
import ru.anishark.domain.repository.BookmarkRepository
import ru.anishark.domain.repository.GenresRepository
import ru.anishark.domain.repository.RecommendationsRepository
import ru.anishark.domain.repository.SeasonsRepository
import ru.anishark.domain.repository.TopRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAnimeRepository(impl: AnimeRepositoryImpl): AnimeRepository

    @Binds
    @Singleton
    abstract fun bindGenresRepository(impl: GenreRepositoryImpl): GenresRepository

    @Binds
    @Singleton
    abstract fun bindRecommendationsRepository(impl: RecommendationsRepositoryImpl): RecommendationsRepository

    @Binds
    @Singleton
    abstract fun bindSeasonsRepository(impl: SeasonsRepositoryImpl): SeasonsRepository

    @Binds
    @Singleton
    abstract fun bindTopRepository(impl: TopRepositoryImpl): TopRepository

    @Binds
    @Singleton
    abstract fun bindBookmarksRepository(impl: BookmarkRepositoryImpl): BookmarkRepository
}
