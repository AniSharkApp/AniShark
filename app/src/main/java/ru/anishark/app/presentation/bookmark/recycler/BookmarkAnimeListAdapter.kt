package ru.anishark.app.presentation.bookmark.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.anishark.app.databinding.CardAnimeBookmarkBinding
import ru.anishark.app.domain.model.BookmarkModel

class BookmarkAnimeListAdapter(
    private var bookmarksList: List<BookmarkModel>,
    private val onClickListener: (Int) -> Unit,

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class BookmarkViewHolder(
        private val binding: CardAnimeBookmarkBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BookmarkModel) {
            binding.bookmarkTitle.text = model.title
            binding.bookmarkImage.load(model.imageUrl)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BookmarkViewHolder {
        val bookmarkAnimeViewBinding =
            CardAnimeBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(bookmarkAnimeViewBinding)
    }

    override fun getItemCount(): Int = bookmarksList.size

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        if (holder is BookmarkViewHolder) {
            holder.bind(bookmarksList[position])
            holder.itemView.setOnClickListener {
                onClickListener(bookmarksList[position].malId)
            }
        }
    }

    fun notifyData(list: List<BookmarkModel>) {
        val diffCallback = BookmarkAnimeListDiffUtilCallback(bookmarksList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        bookmarksList = list
        diffResult.dispatchUpdatesTo(this)
    }
}
