package ru.anishark.app.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.anishark.app.presentation.main.bus.NavigationEventBus
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {
    @Provides
    @Singleton
    fun provideNavigationEventBus(): NavigationEventBus = NavigationEventBus()
}
