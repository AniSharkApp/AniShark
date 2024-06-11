package ru.anishark.app.presentation.bookmark.fragment

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.anishark.app.common.ui.VerticalSpacingItemDecoration
import ru.anishark.app.common.ui.disposeOnDestroy
import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import ru.anishark.app.databinding.FragmentBookmarkBinding
import ru.anishark.app.domain.model.BookmarkModel
import ru.anishark.app.presentation.bookmark.recycler.BookmarkAnimeListAdapter
import ru.anishark.app.presentation.bookmark.viewmodel.BookmarkViewModel
import javax.inject.Inject

@AndroidEntryPoint
class BookmarkFragment : Fragment() {
    private val vm: BookmarkViewModel by viewModels()

    private var bookmarks: List<BookmarkModel> = emptyList()

    private lateinit var binding: FragmentBookmarkBinding
    private val itemDecoration = VerticalSpacingItemDecoration(0f, 12f)

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposable.disposeOnDestroy(this.lifecycle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        disposable += vm.bookmarks
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data -> 
                    bookmarks = data
                },
                // TODO: Сделать красивый обработчик ошибок 
                { error -> 
                    Log.e("MyLog", error.message ?: "empty error")
                }
            )

        with(binding) {
//            val list = MutableList(7) { BookmarkAnimeEntity(animeId = it + 1, image = "", title = "The bast ocean") }


            if (bookmarks.isNotEmpty()) {
                emptyBookmarkFlow.visibility = View.GONE
            }
            bookmarkRv.adapter = BookmarkAnimeListAdapter(bookmarks)
            bookmarkRv.layoutManager = GridLayoutManager(bookmarkRv.context, 2)
            bookmarkRv.addItemDecoration(itemDecoration)
        }

        return binding.root
    }

}