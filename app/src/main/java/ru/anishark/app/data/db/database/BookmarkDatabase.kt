package ru.anishark.app.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.anishark.app.data.db.dao.BookmarkDAO
import ru.anishark.app.data.db.items.BookmarkAnimeEntity

@Database(
    entities = [BookmarkAnimeEntity::class],
    version = 1,
)
abstract class BookmarkDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var database: BookmarkDatabase? = null

        fun getInstance(app: Context): BookmarkDatabase =
            database ?: synchronized(this) {
                database ?: buildDatabase(app).also { database = it }
            }

        private fun buildDatabase(app: Context) =
            Room
                .databaseBuilder(
                    app,
                    BookmarkDatabase::class.java,
                    "bookmarks",
                ).build()
    }

    abstract fun getDao(): BookmarkDAO
}
