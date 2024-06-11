package ru.anishark.app.presentation.filter.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.launch
import ru.anishark.app.R
import ru.anishark.app.presentation.filter.receiveGenreItemsFromApi.RetrofitInstance
import ru.anishark.app.presentation.filter.enums.RatingItems
import ru.anishark.app.presentation.filter.enums.TypeItems
import ru.anishark.app.presentation.filter.expandablelist.ExpandableListAdapter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
const val FIRST_NAME_KEY = "fnk"
const val SECOND_NAME_KEY = "snk"
const val THIRD_NAME_KEY = "tnk"
class FilterActivity : AppCompatActivity() {

    private lateinit var expandableListView: ExpandableListView
    private lateinit var expandableListTitle: List<String>
    private lateinit var expandableListDetail: HashMap<String, List<String>>
    private lateinit var filterBackButton: Button
    private lateinit var filterApplyButton: Button
    var listGenres: ArrayList<String> = ArrayList()
    private val disposables = CompositeDisposable()
    private lateinit var expandableListAdapter: ExpandableListAdapter

    private fun fetchGenres() {
        disposables.add(
            RetrofitInstance.api.getAnimeGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val genres = response.data
                    genres.forEach { genre ->
                        listGenres.add(genre.name + " " + genre.mal_id)
                        Log.d("MainActivity", "Genre: ${genre.name}, ID: ${genre.mal_id}")
                    }
                    expandableListDetail = getData()
                    expandableListAdapter.updateData(expandableListDetail)
                }, { error ->
                    Log.e("MainActivity", "Error fetching genres", error)
                })
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        fetchGenres()

        expandableListView = findViewById(R.id.expandableListView)
        expandableListDetail = getData()
        expandableListTitle = ArrayList(expandableListDetail.keys)
        expandableListAdapter = ExpandableListAdapter(this, expandableListTitle, expandableListDetail)
        expandableListView.setAdapter(expandableListAdapter)

        filterBackButton = findViewById(R.id.filterBackButton)
        filterBackButton.setOnClickListener {
            finish()
        }
        filterApplyButton = findViewById(R.id.filterApplyButton)
        filterApplyButton.setOnClickListener {
            val resultIntent = Intent().apply {
                putStringArrayListExtra(FIRST_NAME_KEY, ArrayList(expandableListAdapter.selectedGenres))
                putStringArrayListExtra(SECOND_NAME_KEY, ArrayList(expandableListAdapter.selectedTypes))
                putStringArrayListExtra(THIRD_NAME_KEY, ArrayList(expandableListAdapter.selectedRatings))
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun getData(): HashMap<String, List<String>> {
        val expandableListDetail = HashMap<String, List<String>>()

        val group1 = listGenres
        val group2 = RatingItems.values().map { it.ratingName }
        val group3 = TypeItems.values().map { it.typeName }

        expandableListDetail["Genres:"] = group1
        expandableListDetail["Types:"] = group2
        expandableListDetail["Rating:"] = group3

        return expandableListDetail
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}