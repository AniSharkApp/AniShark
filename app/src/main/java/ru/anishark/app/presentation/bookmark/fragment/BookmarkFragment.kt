package ru.anishark.app.presentation.bookmark.fragment

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
import ru.anishark.app.databinding.FragmentBookmarkBinding
import ru.anishark.app.domain.model.AnimeModel
import ru.anishark.app.domain.model.BookmarkModel
import ru.anishark.app.presentation.bookmark.recycler.BookmarkAnimeListAdapter
import ru.anishark.app.presentation.bookmark.viewmodel.BookmarkViewModel

@AndroidEntryPoint
class BookmarkFragment : Fragment() {
    private val vm: BookmarkViewModel by viewModels()

    private var bookmarks: List<BookmarkModel> = emptyList()

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private val bookmarkAdapter = BookmarkAnimeListAdapter(bookmarks)

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposable.disposeOnDestroy(this.lifecycle)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        disposable += vm.bookmarks
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data ->
                    bookmarks = data

                    if (data.isEmpty()) {
                        binding.emptyBookmarkFlow.visibility = View.VISIBLE
                    }

                    bookmarkAdapter.notifyData(bookmarks)
                },
                // TODO: Сделать красивый обработчик ошибок
                { error ->
                    Log.e("MyLog", error.message ?: "empty error")
                }
            )

        with(binding) {

            bookmarkRv.adapter = bookmarkAdapter
            bookmarkRv.layoutManager = GridLayoutManager(binding.bookmarkRv.context, 2)
            bookmarkRv.addItemDecoration(VerticalSpacingItemDecoration(0f, 12f))

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}