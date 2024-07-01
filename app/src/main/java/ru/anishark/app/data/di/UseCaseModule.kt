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
import ru.anishark.domain.usecase.DeleteBookmarkCatalogUseCase
import ru.anishark.domain.usecase.GetAllBookmarksCatalogUseCase
import ru.anishark.domain.usecase.GetAnimeByTitleSearchUseCase
import ru.anishark.domain.usecase.GetAnimeSearchUseCase
import ru.anishark.domain.usecase.GetOnClickAnimeUseCase
import ru.anishark.domain.usecase.GetOneBookmarkCatalogUseCase
import ru.anishark.domain.usecase.InsertBookmarkAnimeUseCase
import ru.anishark.domain.usecase.InsertBookmarkCatalogUseCase
import ru.anishark.domain.usecase.LoadActualHomeUseCase
import ru.anishark.domain.usecase.LoadGenresFilterUseCase
import ru.anishark.domain.usecase.LoadTopsHomeUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideLoadActualHomeUseCase(seasonsRepository: SeasonsRepository): LoadActualHomeUseCase = LoadActualHomeUseCase(seasonsRepository)

    @Provides
    @Singleton
    fun provideLoadTopsHomeUseCase(topRepository: TopRepository): LoadTopsHomeUseCase = LoadTopsHomeUseCase(topRepository)

    @Provides
    @Singleton
    fun provideGetAllBookmarksCatalogUseCase(bookmarksRepository: BookmarkRepository): GetAllBookmarksCatalogUseCase =
        GetAllBookmarksCatalogUseCase(bookmarksRepository)

    @Provides
    @Singleton
    fun provideGetOneBookmarkCatalogUseCase(bookmarksRepository: BookmarkRepository): GetOneBookmarkCatalogUseCase =
        GetOneBookmarkCatalogUseCase(bookmarksRepository)

    @Provides
    @Singleton
    fun provideInsertBookmarkCatalogUseCase(bookmarksRepository: BookmarkRepository): InsertBookmarkCatalogUseCase =
        InsertBookmarkCatalogUseCase(bookmarksRepository)

    @Provides
    @Singleton
    fun provideDeleteBookmarkCatalogUseCase(bookmarksRepository: BookmarkRepository): DeleteBookmarkCatalogUseCase =
        DeleteBookmarkCatalogUseCase(bookmarksRepository)

    @Provides
    @Singleton
    fun provideGetAnimeSearchUseCase(animeRepository: AnimeRepository): GetAnimeSearchUseCase = GetAnimeSearchUseCase(animeRepository)

    @Provides
    @Singleton
    fun provideLoadGenresFilterUseCase(genresRepository: GenresRepository): LoadGenresFilterUseCase =
        LoadGenresFilterUseCase(genresRepository)

    @Provides
    @Singleton
    fun provideInsertBookmarkAnimeUseCase(bookmarkRepository: BookmarkRepository): InsertBookmarkAnimeUseCase =
        InsertBookmarkAnimeUseCase(bookmarkRepository)

    @Provides
    @Singleton
    fun provideGetOnClickAnimeUseCase(animeRepository: AnimeRepository): GetOnClickAnimeUseCase = GetOnClickAnimeUseCase(animeRepository)

    @Provides
    @Singleton
    fun provideGetAnimeByTitleSearchUseCase(animeRepository: AnimeRepository): GetAnimeByTitleSearchUseCase =
        GetAnimeByTitleSearchUseCase(animeRepository)
}
