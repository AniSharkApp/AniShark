package ru.anishark.app.presentation.anime.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.anishark.app.R
import ru.anishark.app.common.ui.disposeOnDestroy
import ru.anishark.app.databinding.ActivityAnimeBinding
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.BookmarkModel
import ru.anishark.app.presentation.anime.viewmodel.AnimeViewModel

@AndroidEntryPoint
class AnimeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeBinding

    private val vm: AnimeViewModel by viewModels()

    private var bookmarkState = false

    private val disposable = CompositeDisposable()

    private var currentAnime = AnimeModel(0, "", "", "",0, 0, "", 0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let { screenData ->
            val malId = screenData.getInt("malId")
            vm.loadData(malId)
            disposable +=
                vm
                    .currentAnime
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { data ->
                            currentAnime = data
                            bookmarkState = vm.isAnimeInBookmark
                            changeBookmarkState(bookmarkState)
                            setDataOnView()
                        },
                        { error ->
                            Log.d("MyLog", error.toString())
                        },
                    )
        }
        with(binding) {
            bookmarksScrollView.smoothScrollTo(0, 0)
            icAnimeScreenBack.setOnClickListener {
                finish()
            }

            icAnimeScreenBookmark.setOnClickListener {
                if (bookmarkState) {
                    Toast.makeText(this@AnimeScreenActivity, "Anime Id - ${currentAnime.malId} removed", Toast.LENGTH_SHORT).show()
                    vm
                        .deleteBookmark(currentAnime.malId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
                } else {
                    Toast.makeText(this@AnimeScreenActivity, "Anime Id - ${currentAnime.malId} added", Toast.LENGTH_SHORT).show()
                    vm
                        .insertBookmark(
                            BookmarkModel(
                                malId = currentAnime.malId,
                                imageUrl = currentAnime.imageUrl,
                                title = currentAnime.title
                            )
                        )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
                }
                bookmarkState = !bookmarkState
                changeBookmarkState(bookmarkState)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.disposeOnDestroy(this.lifecycle)
    }

    private fun setDataOnView() {
        disposable +=
            vm.currentAnime
                .subscribe({ model ->
                    with(binding) {
                        backgroundImage.load(model.imageUrl) {
                            placeholder(R.drawable.default_anime_catalog_image)
                            error(R.drawable.default_anime_catalog_image)
                        }
                        mainImage.load(model.imageUrl) {
                            placeholder(R.drawable.default_anime_catalog_image)
                            error(R.drawable.default_anime_catalog_image)
                        }
                        animeTitle.text = model.title
                        animeTitleEnglish.text = model.titleEnglish ?: model.title
                        animeRatingText.text = if (model.score == null) "-" else model.score.toString()
                        animeScreenRatingText.text = if (model.score == null) "-" else model.score.toString()
                        animeScreenDescriptionText.text = model.synopsis
                        changeSeasonIcon(model.season ?: "")
                        animeScreenEpisodesText.text = resources.getString(R.string.episodes, (model.episodes ?: "-"))
                        animeScreenSeasonText.text = resources.getString(R.string.anime_screen_season_text, (model.season ?: "-"))
                        animeScreenStudioText.text = resources.getString(R.string.anime_screen_studio_text, (model.studio ?: "-"))
                    }
                },
                    {
                        Log.d("MyLog", it.message.toString())
                    }
                )

    }

    private fun changeBookmarkState(state: Boolean) {
        if (state) {
            binding.icAnimeScreenBookmark.setImageResource(R.drawable.ic_anime_screen_bookmark_filled)
        } else {
            binding.icAnimeScreenBookmark.setImageResource(R.drawable.ic_anime_screen_bookmark)
        }
    }

    private fun changeSeasonIcon(season: String) {
        when (season) {
            "fall" -> binding.icAnimeScreenSeason.setImageResource(R.drawable.ic_fall)
            "spring" -> binding.icAnimeScreenSeason.setImageResource(R.drawable.ic_spring)
            "winter" -> binding.icAnimeScreenSeason.setImageResource(R.drawable.ic_winter)
            "summer" -> binding.icAnimeScreenSeason.setImageResource(R.drawable.ic_summer)
        }
    }
}
