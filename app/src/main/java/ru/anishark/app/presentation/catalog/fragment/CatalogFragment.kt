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
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.anishark.app.databinding.FragmentCatalogBinding
import ru.anishark.app.presentation.catalog.RetrofitInstance
import ru.anishark.app.presentation.catalog.recycler.AnimeModelForCatalog
import ru.anishark.app.presentation.catalog.recycler.CatalogAnimeListAdapter
import ru.anishark.app.presentation.filter.activity.FilterActivity


const val FIRST_NAME_KEY = "fnk"
const val SECOND_NAME_KEY = "snk"
const val THIRD_NAME_KEY = "tnk"

@AndroidEntryPoint
class CatalogFragment : Fragment() {
    private lateinit var binding: FragmentCatalogBinding
    var list: ArrayList<AnimeModelForCatalog> = ArrayList()
    private lateinit var startForResultLauncher: ActivityResultLauncher<Intent>
    private val disposables = CompositeDisposable()
    private lateinit var type: ArrayList<String>
    private lateinit var rating: ArrayList<String>
    private lateinit var genresList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startForResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.let { intent ->
                    genresList = intent.getStringArrayListExtra(FIRST_NAME_KEY) as ArrayList<String>
                    type = intent.getStringArrayListExtra(SECOND_NAME_KEY) as ArrayList<String>
                    rating = intent.getStringArrayListExtra(THIRD_NAME_KEY) as ArrayList<String>

                    // Fetch the anime list with the received parameters
                    fetchAnimeList()
                }
            }
        }
    }
    private fun returnGenresForApiRequest(list: ArrayList<String>):String{
        var genres : String=""
        list.forEach{genre->
            genres=genres+genre+","
        }
        genres=genres.substring(0,genres.length-1)
        return genres
    }
    private fun fetchAnimeList() {
        if (genresList.size > 0 && type.size > 0 && rating.size > 0) {
            disposables.add(
                RetrofitInstance.api.getAnimeListUsingAllParameters(
                    returnGenresForApiRequest(genresList),
                    type.get(0),
                    rating.get(0)
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        val anime = response.data
                        list.clear()
                        anime.forEach { anime ->
                            list.add(
                                AnimeModelForCatalog(
                                    anime.title,
                                    anime.synopsis ?: "",
                                    anime.episodes ?: 0,
                                    anime.score ?: 0.0,
                                    anime.images.jpg.image_url
                                )
                            )
                        }
                        with(binding) {
                            catalogRv.layoutManager = LinearLayoutManager(catalogRv.context)
                            catalogRv.adapter = CatalogAnimeListAdapter(list)
                        }
                    }, { error ->
                        Log.e("MainActivity", "Error fetching genres", error)
                    })
            )
        } else if (genresList.size > 0 && type.size > 0) {
            disposables.add(
                RetrofitInstance.api.getAnimeListUsingGenresType(
                    returnGenresForApiRequest(genresList),
                    type.get(0),
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        val anime = response.data
                        list.clear()
                        anime.forEach { anime ->
                            list.add(
                                AnimeModelForCatalog(
                                    anime.title,
                                    anime.synopsis ?: "",
                                    anime.episodes ?: 0,
                                    anime.score ?: 0.0,
                                    anime.images.jpg.image_url
                                )
                            )
                        }
                        with(binding) {
                            catalogRv.layoutManager = LinearLayoutManager(catalogRv.context)
                            catalogRv.adapter = CatalogAnimeListAdapter(list)
                        }
                    }, { error ->
                        Log.e("MainActivity", "Error fetching genres", error)
                    })
            )
        } else if (genresList.size > 0 && rating.size > 0) {
            disposables.add(
                RetrofitInstance.api.getAnimeListUsingGenresRating(
                    returnGenresForApiRequest(genresList),
                    rating.get(0)
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        val anime = response.data
                        list.clear()
                        anime.forEach { anime ->
                            list.add(
                                AnimeModelForCatalog(
                                    anime.title,
                                    anime.synopsis ?: "",
                                    anime.episodes ?: 0,
                                    anime.score ?: 0.0,
                                    anime.images.jpg.image_url
                                )
                            )
                        }
                        with(binding) {
                            catalogRv.layoutManager = LinearLayoutManager(catalogRv.context)
                            catalogRv.adapter = CatalogAnimeListAdapter(list)
                        }
                    }, { error ->
                        Log.e("MainActivity", "Error fetching genres", error)
                    })
            )
        } else if (type.size > 0 && rating.size > 0) {
            disposables.add(
                RetrofitInstance.api.getAnimeListUsingTypeRating(
                    type.get(0),
                    rating.get(0)
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        val anime = response.data
                        list.clear()
                        anime.forEach { anime ->
                            list.add(
                                AnimeModelForCatalog(
                                    anime.title,
                                    anime.synopsis ?: "",
                                    anime.episodes ?: 0,
                                    anime.score ?: 0.0,
                                    anime.images.jpg.image_url
                                )
                            )
                        }
                        with(binding) {
                            catalogRv.layoutManager = LinearLayoutManager(catalogRv.context)
                            catalogRv.adapter = CatalogAnimeListAdapter(list)
                        }
                    }, { error ->
                        Log.e("MainActivity", "Error fetching genres", error)
                    })
            )
        } else if (genresList.size > 0) {
            disposables.add(
                RetrofitInstance.api.getAnimeListUsingGenres(
                    returnGenresForApiRequest(genresList),
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        val anime = response.data
                        list.clear()
                        anime.forEach { anime ->
                            list.add(
                                AnimeModelForCatalog(
                                    anime.title,
                                    anime.synopsis ?: "",
                                    anime.episodes ?: 0,
                                    anime.score ?: 0.0,
                                    anime.images.jpg.image_url
                                )
                            )
                        }
                        with(binding) {
                            catalogRv.layoutManager = LinearLayoutManager(catalogRv.context)
                            catalogRv.adapter = CatalogAnimeListAdapter(list)
                        }
                    }, { error ->
                        Log.e("MainActivity", "Error fetching genres", error)
                    })
            )
        } else if (type.size > 0) {
            disposables.add(
                RetrofitInstance.api.getAnimeListUsingType(
                    type.get(0),
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        val anime = response.data
                        list.clear()
                        anime.forEach { anime ->
                            list.add(
                                AnimeModelForCatalog(
                                    anime.title,
                                    anime.synopsis ?: "",
                                    anime.episodes ?: 0,
                                    anime.score ?: 0.0,
                                    anime.images.jpg.image_url
                                )
                            )
                        }
                        with(binding) {
                            catalogRv.layoutManager = LinearLayoutManager(catalogRv.context)
                            catalogRv.adapter = CatalogAnimeListAdapter(list)
                        }
                    }, { error ->
                        Log.e("MainActivity", "Error fetching genres", error)
                    })
            )
        } else if (rating.size > 0) {
            disposables.add(
                RetrofitInstance.api.getAnimeListUsingRating(
                    rating.get(0),
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        val anime = response.data
                        list.clear()
                        anime.forEach { anime ->
                            list.add(
                                AnimeModelForCatalog(
                                    anime.title,
                                    anime.synopsis ?: "",
                                    anime.episodes ?: 0,
                                    anime.score ?: 0.0,
                                    anime.images.jpg.image_url
                                )
                            )
                        }
                        with(binding) {
                            catalogRv.layoutManager = LinearLayoutManager(catalogRv.context)
                            catalogRv.adapter = CatalogAnimeListAdapter(list)
                        }
                    }, { error ->
                        Log.e("MainActivity", "Error fetching genres", error)
                    })
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatalogBinding.inflate(inflater, container, false)
        with(binding) {
            catalogRv.layoutManager = LinearLayoutManager(catalogRv.context)
            catalogRv.adapter = CatalogAnimeListAdapter(list)

            filterButton.setOnClickListener {
                val intent = Intent(filterButton.context, FilterActivity::class.java)
                startForResultLauncher.launch(intent)
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()

    }
}