package ru.anishark.app.presentation.bookmark.fragment

import android.content.res.Resources
import android.database.Observable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.internal.notify
import ru.anishark.app.common.ui.VerticalSpacingItemDecoration
import ru.anishark.app.common.ui.disposeOnDestroy
import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import ru.anishark.app.databinding.FragmentBookmarkBinding
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.BookmarkModel
import ru.anishark.app.presentation.bookmark.recycler.BookmarkAnimeListAdapter
import ru.anishark.app.presentation.bookmark.viewmodel.BookmarkViewModel
import javax.inject.Inject

@AndroidEntryPoint
class BookmarkFragment : Fragment() {
    private val vm: BookmarkViewModel by viewModels()

//    private var bookmarks: List<BookmarkModel> = listOf(BookmarkModel(1,"",""))
    private var bookmarks: List<BookmarkModel> = emptyList()

    private lateinit var binding: FragmentBookmarkBinding

    private val bookmarkAdapter = BookmarkAnimeListAdapter(bookmarks)
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

                    bookmarkAdapter.notifyData(bookmarks)

                    Toast.makeText(context, bookmarks.indices.toString(), Toast.LENGTH_SHORT).show()
                },
                // TODO: Сделать красивый обработчик ошибок
                { error ->
                    Log.e("MyLog", error.message ?: "empty error")
                }
            )
        Toast.makeText(context, bookmarks.indices.toString(), Toast.LENGTH_SHORT).show()
        with(binding) {
            if (bookmarks.isNotEmpty()) {
                emptyBookmarkFlow.visibility = View.GONE
                addBookmark.visibility = View.GONE
            }

            emptyBookmarkFlow.visibility = View.GONE
//            addBookmark.visibility = View.GONE

            binding.bookmarkRv.adapter = bookmarkAdapter
            binding.bookmarkRv.layoutManager = GridLayoutManager(binding.bookmarkRv.context, 2)
            binding.bookmarkRv.addItemDecoration(itemDecoration)

            addBookmark.setOnClickListener {
                vm.insertBookmark(
                    AnimeModel(
                        malId = (0..200).random(),
                        "","",0,0,"",0.1
                    )
                ).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
                Toast.makeText(context, bookmarks.size.toString(), Toast.LENGTH_SHORT).show()
                Log.d("MyLog", bookmarks[bookmarks.lastIndex].toString())
            }

        }

        return binding.root
    }

}