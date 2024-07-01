package ru.anishark.app.presentation.catalog.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.anishark.app.databinding.FragmentCatalogBinding
import ru.anishark.app.domain.model.AnimeGenreModel
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.AnimeRatingModel
import ru.anishark.app.domain.model.AnimeTypeModel
import ru.anishark.app.presentation.anime.activity.AnimeScreenActivity
import ru.anishark.app.presentation.catalog.recycler.CatalogAnimeListAdapter
import ru.anishark.app.presentation.catalog.viewmodel.CatalogViewModel
import ru.anishark.app.presentation.filter.activity.FilterActivity

const val FIRST_NAME_KEY = "fnk"
const val TYPES_KEY = "snk"
const val RATING_KEY = "tnk"

@AndroidEntryPoint
class CatalogFragment : Fragment() {
    private val vm: CatalogViewModel by viewModels()

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    var list: ArrayList<AnimeModel> = ArrayList()
    private lateinit var startForResultLauncher: ActivityResultLauncher<Intent>
    private val disposables = CompositeDisposable()
    private var type: List<AnimeTypeModel> = listOf()
    private var rating: List<AnimeRatingModel> = listOf()
    private var genresList: List<AnimeGenreModel> = listOf()

    private fun startAnimeActivity(malId: Int) {
        val intent = Intent(this@CatalogFragment.context, AnimeScreenActivity::class.java)
        intent.putExtra("malId", malId)
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            catalogRv.layoutManager = LinearLayoutManager(catalogRv.context)
            catalogRv.adapter = CatalogAnimeListAdapter(list, ::startAnimeActivity)

            filterButton.setOnClickListener {
                val intent = Intent(filterButton.context, FilterActivity::class.java)
                startForResultLauncher.launch(intent)
            }
        }

        startForResultLauncher =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult(),
            ) { result ->
                if (result.resultCode == RESULT_OK) {
                    result.data?.let { intent ->
                        // TODO восстановить подгрузку жанров
                        /*val g = intent.getStringArrayListExtra(FIRST_NAME_KEY) as ArrayList<String>
                        genresList = g.map { AnimeGenreModel.fromBackingString() }.toList()*/
                        val t = intent.getStringArrayListExtra(TYPES_KEY) as ArrayList<String>
                        Log.e("AAA", t.toString())
                        type =
                            t
                                .mapNotNull { str -> AnimeTypeModel.values().find { it.displayName == str } }
                                .toList()
                        Log.e("AAA", type.toString())
                        val r = intent.getStringArrayListExtra(RATING_KEY) as ArrayList<String>
                        Log.e("AAA", r.toString())
                        rating =
                            r
                                .mapNotNull { str ->
                                    AnimeRatingModel.getByDisplayName(str)
                                }.toList()
                        Log.e("AAA", rating.toString())
                        vm.searchAnime(ratings = rating, genres = genresList, type = type)
                    }
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fetchAnimeList()
    }

    private fun fetchAnimeList() {
        disposables.add(
            vm
                .searchResults
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        with(binding) {
                            catalogRv.layoutManager = LinearLayoutManager(catalogRv.context)
                            catalogRv.adapter = CatalogAnimeListAdapter(it, ::startAnimeActivity)
                        }
                    },
                    {
                        Log.e("MainActivity", "Error fetching genres", it)
                    },
                ),
        )
        vm.searchAnime(rating, listOf(), type)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
        _binding = null
    }
}
