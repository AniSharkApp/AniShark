package ru.anishark.app.data.db.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.anishark.app.data.db.dao.ActualAnimeDAO
import ru.anishark.app.data.db.mapper.ActualMapper
import ru.anishark.app.domain.model.AnimeModel
import javax.inject.Inject

class DatabaseActualDataSource @Inject constructor(
    private val dao: ActualAnimeDAO,
    private val actualMapper: ActualMapper,
) {
    fun getAll(): Observable<List<AnimeModel>> =
        dao.getAll().map {
            it.map(actualMapper::toDomain)
        }

    fun insert(models: List<AnimeModel>): Completable = dao.insert(*models.map(actualMapper::fromDomainModel).toTypedArray())

    fun deleteAll(): Completable = dao.deleteAll()
}
