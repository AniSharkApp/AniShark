package ru.anishark.app.presentation.search.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.anishark.app.common.ui.disposeOnDestroy
import ru.anishark.app.databinding.FragmentSearchBinding
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.presentation.anime.activity.AnimeScreenActivity
import ru.anishark.app.presentation.catalog.recycler.CatalogAnimeListAdapter
import ru.anishark.app.presentation.search.adapter.SearchAnimeListAdapter
import ru.anishark.app.presentation.search.viewmodel.SearchViewModel

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var disposable = CompositeDisposable()

    private val vm: SearchViewModel by viewModels()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    var list: List<AnimeModel> = emptyList()

    private fun startAnimeActivity(malId: Int) {
        val intent = Intent(this@SearchFragment.context, AnimeScreenActivity::class.java)
        intent.putExtra("malId", malId)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposable.disposeOnDestroy(this.lifecycle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        val mainActivity = requireActivity()
        mainActivity.onBackPressedDispatcher.addCallback(this) {
            mainActivity.supportFragmentManager.popBackStackImmediate()
        }

        val searchText: String = arguments?.getString("SEARCH") ?: ""
        vm.searchAnime(searchText)

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentSearch.setOnClickListener {
            // TODO: это заглушка
            Log.d("MyLog", "Fragment clicked")
        }

        disposable +=
            vm.searchResults
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        with(binding) {
                            searchRv.layoutManager = LinearLayoutManager(searchRv.context)
                            searchRv.adapter = SearchAnimeListAdapter(it, ::startAnimeActivity)
                        }
                        Toast.makeText(context, "Данные полученны", Toast.LENGTH_SHORT).show()
                    },
                    {
                        Log.e("MyLog", "Error getting search results")
                    }
                )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
