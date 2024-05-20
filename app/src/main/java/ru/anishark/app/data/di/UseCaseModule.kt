package ru.anishark.app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.anishark.app.domain.repository.SeasonsRepository
import ru.anishark.app.domain.repository.TopRepository
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
}