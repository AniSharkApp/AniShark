package ru.anishark.data.database.datasource

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.anishark.data.database.dao.TopAnimeDAO
import ru.anishark.data.database.mapper.TopMapper
import ru.anishark.domain.model.AnimeModel
import javax.inject.Inject

class DatabaseTopDataSource @Inject constructor(
    private val dao: TopAnimeDAO,
    private val topMapper: TopMapper,
) {
    fun getAll(): Observable<List<AnimeModel>> =
        dao.getAll().map {
            it.map(topMapper::toDomain)
        }

    fun insert(models: List<AnimeModel>): Completable = dao.insert(*models.map(topMapper::fromDomainModel).toTypedArray())

    fun deleteAll(): Completable = dao.deleteAll()
}
