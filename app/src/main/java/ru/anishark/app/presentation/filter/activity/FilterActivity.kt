package ru.anishark.app.presentation.filter.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
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

class FilterActivity : AppCompatActivity() {

    lateinit var expandableListView: ExpandableListView
    private lateinit var expandableListTitle: List<String>
    private lateinit var expandableListDetail: HashMap<String, List<String>>
    private lateinit var filterBackButton: Button
    var listGenres: ArrayList<String> = ArrayList()
    private val disposables = CompositeDisposable()

    private fun fetchGenres() {
        disposables.add(
            RetrofitInstance.api.getAnimeGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val genres = response.data
                    genres.forEach { genre ->
                        listGenres.add(genre.name)
                        Log.d("MainActivity", "Genre: ${genre.name}, ID: ${genre.mal_id}")
                    }
                    // Обновите адаптер после загрузки данных
                    expandableListDetail = getData()
                    (expandableListView.expandableListAdapter as ExpandableListAdapter).updateData(expandableListDetail)
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
        val expandableListAdapter = ExpandableListAdapter(this, expandableListTitle, expandableListDetail)
        expandableListView.setAdapter(expandableListAdapter)

        filterBackButton = findViewById(R.id.filterBackButton)
        filterBackButton.setOnClickListener {
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