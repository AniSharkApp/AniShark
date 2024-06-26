package ru.anishark.app.presentation.filter.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.anishark.app.databinding.ActivityFilterBinding
import ru.anishark.app.domain.model.AnimeGenreModel
import ru.anishark.app.domain.model.AnimeRatingModel
import ru.anishark.app.domain.model.AnimeTypeModel
import ru.anishark.app.presentation.catalog.fragment.FIRST_NAME_KEY
import ru.anishark.app.presentation.catalog.fragment.RATING_KEY
import ru.anishark.app.presentation.catalog.fragment.TYPES_KEY
import ru.anishark.app.presentation.filter.expandablelist.ExpandableListAdapter
import ru.anishark.app.presentation.filter.viewmodel.FilterViewModel

@AndroidEntryPoint
class FilterActivity : AppCompatActivity() {
    private var binding: ActivityFilterBinding? = null
    private lateinit var expandableListTitle: List<String>
    private lateinit var expandableListDetail: Map<String, List<String>>
    var genresNameAndId: MutableMap<String, String> = mutableMapOf()
    var listGenres: List<AnimeGenreModel> = listOf()
    private val disposables = CompositeDisposable()
    private lateinit var expandableListAdapter: ExpandableListAdapter

    private val vm: FilterViewModel by viewModels()

    private fun fetchGenres() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        disposables.add(
            vm.genres.subscribe(
                { genres ->
                    listGenres = genres
                    genres.forEach { genre ->
                        genresNameAndId.put("${genre.name}", "${genre.malId}")
                        Log.i("MainActivity", "Genre: ${genre.name}, ID: ${genre.malId}")
                    }
                    expandableListDetail = getData()
                    expandableListAdapter.updateData(expandableListDetail)
                },
                { error ->
                    Log.e("MainActivity", "Error fetching genres", error)
                },
            ),
        )
        vm.loadGenres()

        with(binding!!) {
            expandableListDetail = getData()
            expandableListTitle = ArrayList(expandableListDetail.keys)
            expandableListAdapter =
                ExpandableListAdapter(
                    this@FilterActivity,
                    expandableListTitle,
                    expandableListDetail,
                )
            expandableListView.setAdapter(expandableListAdapter)

            filterBackButton.setOnClickListener {
                finish()
            }

            filterApplyButton.setOnClickListener {
                val resultIntent =
                    Intent().apply {
                        putStringArrayListExtra(
                            FIRST_NAME_KEY,
                            getIdSelectedGenres(ArrayList(expandableListAdapter.selectedGenres) as ArrayList<String>),
                        )
                        putStringArrayListExtra(TYPES_KEY, ArrayList(expandableListAdapter.selectedTypes))
                        putStringArrayListExtra(RATING_KEY, ArrayList(expandableListAdapter.selectedRatings))
                    }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    private fun getIdSelectedGenres(list: ArrayList<String>): ArrayList<String> {
        var listId: ArrayList<String> = ArrayList()
        list.forEach { animeName ->
            listId.add(genresNameAndId.get(animeName).toString())
        }
        return listId
    }

    private fun getData(): Map<String, List<String>> {
        val expandableListDetail = mutableMapOf<String, List<String>>()

        val genres = listGenres
        val ratings = AnimeRatingModel.values().map { it.displayName }
        val types = AnimeTypeModel.values().map { it.displayName }

        expandableListDetail["Genres:"] = genres.map { it.name }
        expandableListDetail["Rating:"] = ratings
        expandableListDetail["Types:"] = types

        return expandableListDetail
    }

    override fun onDestroy() {
        disposables.clear()
        binding = null
        super.onDestroy()
    }
}
