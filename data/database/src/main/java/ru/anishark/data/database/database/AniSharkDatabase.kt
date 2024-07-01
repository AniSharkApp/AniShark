package ru.anishark.data.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.anishark.data.database.dao.ActualAnimeDAO
import ru.anishark.data.database.dao.BookmarkDAO
import ru.anishark.data.database.dao.TopAnimeDAO
import ru.anishark.data.database.dbo.ActualAnimeDBO
import ru.anishark.data.database.dbo.BookmarkDBO
import ru.anishark.data.database.dbo.TopAnimeDBO

@Database(
    entities = [BookmarkDBO::class, TopAnimeDBO::class, ActualAnimeDBO::class],
    version = 1,
)
abstract class AniSharkDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var database: AniSharkDatabase? = null

        fun getInstance(app: Context): AniSharkDatabase =
            database ?: synchronized(this) {
                database ?: buildDatabase(app).also { database = it }
            }

        private fun buildDatabase(app: Context) =
            Room
                .databaseBuilder(
                    app,
                    AniSharkDatabase::class.java,
                    "bookmarks",
                ).build()
    }

    abstract fun getBookmarkDao(): BookmarkDAO

    abstract fun getActualDao(): ActualAnimeDAO

    abstract fun getTopDao(): TopAnimeDAO
}
