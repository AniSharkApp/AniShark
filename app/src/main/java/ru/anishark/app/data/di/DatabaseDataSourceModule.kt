package ru.anishark.app.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.anishark.app.data.db.database.BookmarkDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseDataSourceModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext app: Context,
    ) = BookmarkDatabase.getInstance(app)

    @Provides
    @Singleton
    fun provideBookmarkDao(db: BookmarkDatabase) = db.getDao()
}
