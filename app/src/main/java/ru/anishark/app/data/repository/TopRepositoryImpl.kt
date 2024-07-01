package ru.anishark.app.data.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.anishark.app.data.db.datasource.DatabaseTopDataSource
import ru.anishark.app.data.remote.datasource.RemoteTopDataSource
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.repository.TopRepository
import javax.inject.Inject

class TopRepositoryImpl @Inject constructor(
    private val remoteTopDataSource: RemoteTopDataSource,
    private val databaseTopDataSource: DatabaseTopDataSource,
) : TopRepository {
    private val compositeDisposable = CompositeDisposable()

    override fun getTopAnime(): Observable<List<AnimeModel>> {
        remoteTopDataSource
            .getTopAnime()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    databaseTopDataSource
                        .deleteAll()
                        .subscribeOn(Schedulers.io())
                        .subscribe()
                    databaseTopDataSource
                        .insert(it)
                        .subscribeOn(Schedulers.io())
                        .subscribe()
                },
                {
                },
                compositeDisposable,
            )
        return databaseTopDataSource
            .getAll()
            .filter { it.isNotEmpty() }
    }
}
