package ru.anishark.app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.anishark.app.data.db.datasource.DatabaseBookmarksDataSource
import ru.anishark.app.data.repository.BookmarkRepositoryImpl
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
       bookmarksDataSource: DatabaseBookmarksDataSource
    ): GetAllBookmarksCatalogUseCase = GetAllBookmarksCatalogUseCase(bookmarksDataSource)

    @Provides
    @Singleton
    fun provideGetOneBookmarkCatalogUseCase(
        bookmarksDataSource: DatabaseBookmarksDataSource
    ): GetOneBookmarkCatalogUseCase = GetOneBookmarkCatalogUseCase(bookmarksDataSource)

    @Provides
    @Singleton
    fun provideInsertBookmarkCatalogUseCase(
        bookmarksDataSource: DatabaseBookmarksDataSource
    ): InsertBookmarkCatalogUseCase = InsertBookmarkCatalogUseCase(bookmarksDataSource)

    @Provides
    @Singleton
    fun provideDeleteBookmarkCatalogUseCase(
        bookmarksDataSource: DatabaseBookmarksDataSource
    ): DeleteBookmarkCatalogUseCase = DeleteBookmarkCatalogUseCase(bookmarksDataSource)
}