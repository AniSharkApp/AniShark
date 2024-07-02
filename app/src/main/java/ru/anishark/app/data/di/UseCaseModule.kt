package ru.anishark.app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.anishark.domain.repository.AnimeRepository
import ru.anishark.domain.repository.BookmarkRepository
import ru.anishark.domain.repository.GenresRepository
import ru.anishark.domain.repository.SeasonsRepository
import ru.anishark.domain.repository.TopRepository
import ru.anishark.domain.usecase.AddBookmarkAnimeUseCase
import ru.anishark.domain.usecase.AddBookmarkCatalogUseCase
import ru.anishark.domain.usecase.GetActualAnimeUseCase
import ru.anishark.domain.usecase.GetAllBookmarksUseCase
import ru.anishark.domain.usecase.GetAnimeDetailsUseCase
import ru.anishark.domain.usecase.GetAnimeSearchUseCase
import ru.anishark.domain.usecase.GetFilterGenresUseCase
import ru.anishark.domain.usecase.GetFilteredAnimeUseCase
import ru.anishark.domain.usecase.GetOneBookmarkUseCase
import ru.anishark.domain.usecase.GetTopAnimeUseCase
import ru.anishark.domain.usecase.RemoveBookmarkUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideLoadActualHomeUseCase(seasonsRepository: SeasonsRepository): GetActualAnimeUseCase = GetActualAnimeUseCase(seasonsRepository)

    @Provides
    @Singleton
    fun provideLoadTopsHomeUseCase(topRepository: TopRepository): GetTopAnimeUseCase = GetTopAnimeUseCase(topRepository)

    @Provides
    @Singleton
    fun provideGetAllBookmarksCatalogUseCase(bookmarksRepository: BookmarkRepository): GetAllBookmarksUseCase =
        GetAllBookmarksUseCase(bookmarksRepository)

    @Provides
    @Singleton
    fun provideGetOneBookmarkCatalogUseCase(bookmarksRepository: BookmarkRepository): GetOneBookmarkUseCase =
        GetOneBookmarkUseCase(bookmarksRepository)

    @Provides
    @Singleton
    fun provideInsertBookmarkCatalogUseCase(bookmarksRepository: BookmarkRepository): AddBookmarkCatalogUseCase =
        AddBookmarkCatalogUseCase(bookmarksRepository)

    @Provides
    @Singleton
    fun provideDeleteBookmarkCatalogUseCase(bookmarksRepository: BookmarkRepository): RemoveBookmarkUseCase =
        RemoveBookmarkUseCase(bookmarksRepository)

    @Provides
    @Singleton
    fun provideGetAnimeSearchUseCase(animeRepository: AnimeRepository): GetFilteredAnimeUseCase = GetFilteredAnimeUseCase(animeRepository)

    @Provides
    @Singleton
    fun provideLoadGenresFilterUseCase(genresRepository: GenresRepository): GetFilterGenresUseCase =
        GetFilterGenresUseCase(genresRepository)

    @Provides
    @Singleton
    fun provideInsertBookmarkAnimeUseCase(bookmarkRepository: BookmarkRepository): AddBookmarkAnimeUseCase =
        AddBookmarkAnimeUseCase(bookmarkRepository)

    @Provides
    @Singleton
    fun provideGetOnClickAnimeUseCase(animeRepository: AnimeRepository): GetAnimeDetailsUseCase = GetAnimeDetailsUseCase(animeRepository)

    @Provides
    @Singleton
    fun provideGetAnimeByTitleSearchUseCase(animeRepository: AnimeRepository): GetAnimeSearchUseCase =
        GetAnimeSearchUseCase(animeRepository)
}
