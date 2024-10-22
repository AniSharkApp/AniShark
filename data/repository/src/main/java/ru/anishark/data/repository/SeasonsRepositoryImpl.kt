package ru.anishark.data.repository

import android.annotation.SuppressLint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.anishark.data.database.datasource.DatabaseActualDataSource
import ru.anishark.data.remote.datasource.RemoteSeasonsDataSource
import ru.anishark.domain.model.AnimeModel
import ru.anishark.domain.repository.SeasonsRepository
import javax.inject.Inject

class SeasonsRepositoryImpl @Inject constructor(
    private val remoteSeasonsDataSource: RemoteSeasonsDataSource,
    private val dabataseSeasonsDataSource: DatabaseActualDataSource,
) : SeasonsRepository {
    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun getCurrentSeasonAnime(): Observable<List<AnimeModel>> {
        remoteSeasonsDataSource
            .getSeasonsNowAnime()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    dabataseSeasonsDataSource
                        .deleteAll()
                        .subscribeOn(Schedulers.io())
                        .subscribe()
                    dabataseSeasonsDataSource
                        .insert(it)
                        .subscribeOn(Schedulers.io())
                        .subscribe()
                },
                {
                },
                compositeDisposable,
            )
        return dabataseSeasonsDataSource
            .getAll()
            .filter { it.isNotEmpty() }
    }
}
