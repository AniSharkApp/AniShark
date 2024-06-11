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
import ru.anishark.app.common.ui.VerticalSpacingItemDecoration
import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import ru.anishark.app.databinding.FragmentBookmarkBinding
import ru.anishark.app.presentation.bookmark.recycler.BookmarkAnimeListAdapter
import ru.anishark.app.presentation.bookmark.viewmodel.BookmarkViewModel
import javax.inject.Inject

@AndroidEntryPoint
class BookmarkFragment : Fragment() {
    private val vm: BookmarkViewModel by viewModels()

    private lateinit var binding: FragmentBookmarkBinding
    private val itemDecoration = VerticalSpacingItemDecoration(0f, 12f)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        with(binding) {
            val list = MutableList(7) { BookmarkAnimeEntity(animeId = it + 1, image = "", title = "The bast ocean") }
            // TODO: Переделать лист на лист из vm
            if (list.isNotEmpty()) {
                emptyBookmarkFlow.visibility = View.GONE
            }
            bookmarkRv.adapter = BookmarkAnimeListAdapter(list)
            bookmarkRv.layoutManager = GridLayoutManager(bookmarkRv.context, 2)
            bookmarkRv.addItemDecoration(itemDecoration)
        }

        return binding.root
    }

}