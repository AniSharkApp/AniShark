package ru.anishark.app.presentation.anime

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.anishark.app.R
import ru.anishark.app.common.ui.disposeOnDestroy
import ru.anishark.app.databinding.ActivityAnimeBinding
import ru.anishark.app.domain.model.BookmarkModel
import ru.anishark.app.presentation.anime.viewmodel.AnimeViewModel

@AndroidEntryPoint
class AnimeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeBinding

    private val vm: AnimeViewModel by viewModels()

    private var bookmarkState = false

    // TODO: переделать на человеческий, но я не хочу null
    private var currentAnime: BookmarkModel = BookmarkModel(0, "", "")

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let { screenData ->
            val malId = screenData.getInt("malId")
            disposable +=
                vm
                    .getBookmark(malId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { data ->
                            currentAnime = data
                            bookmarkState = true
                            changeBookmarkState(bookmarkState)
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
                    vm
                        .deleteBookmark(currentAnime.malId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
                } else {
                    vm
                        .insertBookmark(currentAnime)
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

    private fun changeBookmarkState(state: Boolean) {
        if (state) {
            binding.icAnimeScreenBookmark.setImageResource(R.drawable.ic_anime_screen_bookmark_filled)
        } else {
            binding.icAnimeScreenBookmark.setImageResource(R.drawable.ic_anime_screen_bookmark)
        }
    }
}
