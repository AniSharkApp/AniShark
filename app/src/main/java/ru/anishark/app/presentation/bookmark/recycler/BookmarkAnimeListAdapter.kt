package ru.anishark.app.presentation.bookmark.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import ru.anishark.app.R
import ru.anishark.app.data.db.items.BookmarkAnimeEntity
import ru.anishark.app.databinding.CardAnimeBookmarkBinding

class BookmarkAnimeListAdapter(private val bookmarksList: List<BookmarkAnimeEntity>)
    :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        class BookmarkViewHolder(private val binding: CardAnimeBookmarkBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(model: BookmarkAnimeEntity) {
                binding.bookmarkTitle.text = model.title
                // TODO: переделать на человеческий
                binding.bookmarkImage.setImageDrawable(AppCompatResources.getDrawable(this.itemView.context, R.drawable.default_anime_image))
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val bookmarkAnimeViewBinding =
            CardAnimeBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(bookmarkAnimeViewBinding)
    }

    override fun getItemCount(): Int = bookmarksList.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookmarkViewHolder) {
            holder.bind(bookmarksList[position])
        }
    }
}