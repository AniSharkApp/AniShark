package ru.anishark.app.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.anishark.app.data.db.dao.BookmarkDAO
import ru.anishark.app.data.db.items.BookmarkAnime

@Database(
    entities = [BookmarkAnime::class],
    version = 1
)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun getDao(): BookmarkDAO
}