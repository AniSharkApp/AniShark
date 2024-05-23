package ru.anishark.app.presentation.home.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.anishark.app.domain.model.AnimeModel

class HomeAnimeListDiffUtilCallback(
    private val oldList: List<AnimeModel>,
    private val newList: List<AnimeModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].malId == newList[newItemPosition].malId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}