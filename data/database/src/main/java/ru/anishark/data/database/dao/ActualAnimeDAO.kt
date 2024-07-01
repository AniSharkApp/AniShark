package ru.anishark.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.anishark.data.database.dbo.ActualAnimeDBO

@Dao
interface ActualAnimeDAO {
    @Query("SELECT * FROM actualAnime LIMIT 20")
    fun getAll(): Observable<List<ActualAnimeDBO>>

    @Upsert
    fun insert(vararg anime: ActualAnimeDBO): Completable

    @Query("DELETE FROM actualAnime WHERE 1 = 1")
    fun deleteAll(): Completable
}
