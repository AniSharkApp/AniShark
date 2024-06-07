package ru.anishark.app.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.anishark.app.data.db.dao.BookmarkDAO
import ru.anishark.app.data.db.items.BookmarkAnime

@Database(
    entities = [BookmarkAnime::class],
    version = 1
)
abstract class BookmarkDatabase : RoomDatabase() {
    companion object {

        @Volatile
        private var INSTANCE: BookmarkDatabase? = null

        fun getInstance(app: Context): BookmarkDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(app).also { INSTANCE = it }
        }

        private fun buildDatabase(app: Context) =
            Room.databaseBuilder(app,
                BookmarkDatabase::class.java,
                "your database name")
                .build()
    }

    abstract fun getDao(): BookmarkDAO
}