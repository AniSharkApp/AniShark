package ru.anishark.app.presentation.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.anishark.app.databinding.FragmentHomeBinding
import ru.anishark.app.presentation.home.recycler.HomeAnimeListAdapter
import ru.anishark.app.presentation.home.viewmodel.HomeViewModel
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor() : Fragment() {
    private val vm: HomeViewModel by viewModels()

    private val topsAdapter = HomeAnimeListAdapter()
    private val actualAdapter = HomeAnimeListAdapter()

    private val disposable = CompositeDisposable()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        with(binding) {
            // TODO объединить несколько RV с заголовками в один RV.
            topsRv.adapter = topsAdapter
            topsRv.layoutManager = LinearLayoutManager(
                topsRv.context, LinearLayoutManager.HORIZONTAL, false
            )
            actualRv.adapter = actualAdapter
            actualRv.layoutManager = LinearLayoutManager(
                actualRv.context, LinearLayoutManager.HORIZONTAL, false
            )
            disposable += vm.topsState
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        topsAdapter.dataLoaded(it)
                    },
                    {
                        Log.e("APP", "${it.message}\n\n${Log.getStackTraceString(it)}")
                        topsAdapter.loadError(it.message)
                    }
                )
            disposable += vm.actualState
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        actualAdapter.dataLoaded(it)
                    },
                    {
                        Log.e("APP", "${it.message}\n\n${Log.getStackTraceString(it)}")
                        actualAdapter.loadError(it.message)
                    }
                )
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}