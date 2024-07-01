package ru.anishark.app.presentation.bookmark.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.anishark.domain.model.BookmarkModel

class BookmarkAnimeListDiffUtilCallback(
    private val oldList: List<BookmarkModel>,
    private val newList: List<BookmarkModel>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Boolean = oldList[oldItemPosition].malId == newList[newItemPosition].malId

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
    ): Boolean = oldList[oldItemPosition] == newList[newItemPosition]
}
