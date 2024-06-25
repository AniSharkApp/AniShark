package ru.anishark.app.presentation.home.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import ru.anishark.app.common.ui.HorizontalSpacingItemDecoration
import ru.anishark.app.common.ui.disposeOnDestroy
import ru.anishark.app.databinding.FragmentHomeBinding
import ru.anishark.app.presentation.anime.AnimeScreenActivity
import ru.anishark.app.presentation.home.recycler.HomeAnimeListAdapter
import ru.anishark.app.presentation.home.viewmodel.HomeViewModel

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val vm: HomeViewModel by viewModels()

    // TODO использовать dimens ресурс
    private val itemDecoration = HorizontalSpacingItemDecoration(0f, 12f)

    private val disposable = CompositeDisposable()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposable.disposeOnDestroy(this.lifecycle)
    }

    private fun startAnimeActivity(malId: Int) {
        val intent = Intent(this@HomeFragment.context, AnimeScreenActivity::class.java)
        intent.putExtra("malId", malId)
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        vm.loadAllData()
        with(binding) {
            // TODO объединить несколько RV с заголовками в один RV.
            val topsAdapter = HomeAnimeListAdapter(::startAnimeActivity)
            topsRv.adapter = topsAdapter
            topsRv.layoutManager =
                LinearLayoutManager(
                    topsRv.context,
                    LinearLayoutManager.HORIZONTAL,
                    false,
                )
            topsRv.addItemDecoration(itemDecoration)
            val actualAdapter = HomeAnimeListAdapter(::startAnimeActivity)
            actualRv.adapter = actualAdapter
            actualRv.layoutManager =
                LinearLayoutManager(
                    actualRv.context,
                    LinearLayoutManager.HORIZONTAL,
                    false,
                )
            actualRv.addItemDecoration(itemDecoration)
            disposable +=
                vm.topsState
                    .subscribe(
                        {
                            val topsRVState = topsRv.layoutManager?.onSaveInstanceState()
                            topsAdapter.dataLoaded(it)
                            topsRv.layoutManager?.onRestoreInstanceState(topsRVState)
                        },
                        {
                            Log.e("APP", "${it.message}\n\n${Log.getStackTraceString(it)}")
                            topsAdapter.loadError(it.message)
                        },
                    )
            disposable +=
                vm.actualState
                    .subscribe(
                        {
                            val actualRVState = actualRv.layoutManager?.onSaveInstanceState()
                            actualAdapter.dataLoaded(it)
                            actualRv.layoutManager?.onRestoreInstanceState(actualRVState)
                        },
                        {
                            Log.e("APP", "${it.message}\n\n${Log.getStackTraceString(it)}")
                            actualAdapter.loadError(it.message)
                        },
                    )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
