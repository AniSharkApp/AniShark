package ru.anishark.app.presentation.bookmark.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.anishark.app.common.ui.VerticalSpacingItemDecoration
import ru.anishark.app.common.ui.disposeOnDestroy
import ru.anishark.app.databinding.FragmentBookmarkBinding
import ru.anishark.app.presentation.anime.AnimeScreenActivity
import ru.anishark.app.presentation.bookmark.recycler.BookmarkAnimeListAdapter
import ru.anishark.app.presentation.bookmark.recycler.BookmarksState
import ru.anishark.app.presentation.bookmark.viewmodel.BookmarkViewModel

@AndroidEntryPoint
class BookmarkFragment : Fragment() {
    private val vm: BookmarkViewModel by viewModels()

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private val bookmarkAdapter = BookmarkAnimeListAdapter(::startAnimeActivity)

    private val disposable = CompositeDisposable()

    private fun startAnimeActivity(malId: Int) {
        val intent = Intent(this@BookmarkFragment.context, AnimeScreenActivity::class.java)
        intent.putExtra("malId", malId)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposable.disposeOnDestroy(this.lifecycle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        vm.loadBookmarks()

        disposable +=
            vm.bookmarksState
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it is BookmarksState.Content) {
                        binding.bookmarkRv.layoutManager = GridLayoutManager(binding.bookmarkRv.context, 2)
                    } else {
                        binding.bookmarkRv.layoutManager = LinearLayoutManager(binding.bookmarkRv.context)
                    }
                    bookmarkAdapter.updateState(it)
                }

        with(binding) {
            bookmarkRv.adapter = bookmarkAdapter
            bookmarkRv.layoutManager = LinearLayoutManager(binding.bookmarkRv.context)
            bookmarkRv.addItemDecoration(VerticalSpacingItemDecoration(0f, 12f))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
