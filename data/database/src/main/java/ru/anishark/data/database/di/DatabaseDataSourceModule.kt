package ru.anishark.data.database.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.anishark.data.database.database.AniSharkDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseDataSourceModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext app: Context,
    ) = AniSharkDatabase.getInstance(app)

    @Provides
    @Singleton
    fun provideBookmarkDao(db: AniSharkDatabase) = db.getBookmarkDao()

    @Provides
    @Singleton
    fun provideActualDao(db: AniSharkDatabase) = db.getActualDao()

    @Provides
    @Singleton
    fun provideTopDao(db: AniSharkDatabase) = db.getTopDao()
}
