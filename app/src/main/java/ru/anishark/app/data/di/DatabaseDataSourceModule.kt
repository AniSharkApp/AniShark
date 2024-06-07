package ru.anishark.app.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.anishark.app.data.db.database.BookmarkDatabase
import javax.inject.Singleton

@Module
class DatabaseDataSourceModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context) = BookmarkDatabase.getInstance(app)

    @Provides
    @Singleton
    fun provideBookmarkDao(db: BookmarkDatabase) = db.getDao()
}