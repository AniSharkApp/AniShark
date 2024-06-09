package ru.anishark.app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.anishark.app.data.db.repository.BookmarkRepository
import ru.anishark.app.domain.repository.SeasonsRepository
import ru.anishark.app.domain.repository.TopRepository
import ru.anishark.app.domain.usecase.DeleteBookmarkCatalogUseCase
import ru.anishark.app.domain.usecase.GetAllBookmarksCatalogUseCase
import ru.anishark.app.domain.usecase.GetOneBookmarkCatalogUseCase
import ru.anishark.app.domain.usecase.InsertBookmarkCatalogUseCase
import ru.anishark.app.domain.usecase.LoadActualHomeUseCase
import ru.anishark.app.domain.usecase.LoadTopsHomeUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideLoadActualHomeUseCase(
        seasonsRepository: SeasonsRepository
    ): LoadActualHomeUseCase = LoadActualHomeUseCase(seasonsRepository)

    @Provides
    @Singleton
    fun provideLoadTopsHomeUseCase(
        topRepository: TopRepository
    ): LoadTopsHomeUseCase = LoadTopsHomeUseCase(topRepository)

    @Provides
    @Singleton
    fun provideGetAllBookmarksCatalogUseCase(
        bookmarksRepository: BookmarkRepository
    ): GetAllBookmarksCatalogUseCase = GetAllBookmarksCatalogUseCase(bookmarksRepository)

    @Provides
    @Singleton
    fun provideGetOneBookmarkCatalogUseCase(
        bookmarksRepository: BookmarkRepository
    ): GetOneBookmarkCatalogUseCase = GetOneBookmarkCatalogUseCase(bookmarksRepository)

    @Provides
    @Singleton
    fun provideInsertBookmarkCatalogUseCase(
        bookmarksRepository: BookmarkRepository
    ): InsertBookmarkCatalogUseCase = InsertBookmarkCatalogUseCase(bookmarksRepository)

    @Provides
    @Singleton
    fun provideDeleteBookmarkCatalogUseCase(
        bookmarksRepository: BookmarkRepository
    ): DeleteBookmarkCatalogUseCase = DeleteBookmarkCatalogUseCase(bookmarksRepository)
}