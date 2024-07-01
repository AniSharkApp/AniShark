package ru.anishark.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.anishark.data.database.dbo.TopAnimeDBO

@Dao
interface TopAnimeDAO {
    @Query("SELECT * FROM topAnime")
    fun getAll(): Observable<List<TopAnimeDBO>>

    @Upsert
    fun insert(vararg anime: TopAnimeDBO): Completable

    @Query("DELETE FROM topAnime WHERE 1 = 1")
    fun deleteAll(): Completable
}
