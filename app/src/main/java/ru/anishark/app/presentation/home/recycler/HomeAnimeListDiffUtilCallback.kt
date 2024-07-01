package ru.anishark.app.presentation.home.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.anishark.domain.model.AnimeModel

class HomeAnimeListDiffUtilCallback(
    private val oldList: List<AnimeModel>,
    private val newList: List<AnimeModel>,
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
