package ru.anishark.app.presentation.bookmark.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.anishark.app.databinding.CardAnimeBookmarkBinding
import ru.anishark.app.databinding.CardEmptyBookmarksBinding
import ru.anishark.app.databinding.LayoutErrorBinding
import ru.anishark.app.databinding.LayoutLoadingBinding
import ru.anishark.app.domain.model.BookmarkModel

class BookmarkAnimeListAdapter(
    private val onClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var state: BookmarksState = BookmarksState.Loading

    class EmptyBookmarksViewHolder(
        val binding: CardEmptyBookmarksBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class BookmarkViewHolder(
        private val binding: CardAnimeBookmarkBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BookmarkModel) {
            binding.bookmarkTitle.text = model.title
            binding.bookmarkImage.load(model.imageUrl)
        }
    }

    class LoadingViewHolder(
        val binding: LayoutLoadingBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class ErrorViewHolder(
        val binding: LayoutErrorBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: String) {
            with(binding) {
                errorTv.text = message
            }
        }
    }

    override fun getItemViewType(position: Int): Int = state.itemType

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder =
        when (viewType) {
            BookmarksState.Loading.itemType -> {
                val loadingBinding =
                    LayoutLoadingBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                LoadingViewHolder(loadingBinding)
            }

            BookmarksState.Error("Помогите, я китайский мальчик").itemType -> {
                val errorBinding =
                    LayoutErrorBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                ErrorViewHolder(errorBinding)
            }

            BookmarksState.EmptyContent.itemType -> {
                val emptyContentBinding =
                    CardEmptyBookmarksBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                EmptyBookmarksViewHolder(emptyContentBinding)
            }

            else -> {
                val bookmarkAnimeViewBinding =
                    CardAnimeBookmarkBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                BookmarkViewHolder(bookmarkAnimeViewBinding)
            }
        }

    override fun getItemCount(): Int {
        return if (state is BookmarksState.Content) (state as BookmarksState.Content).data.size
        else 1
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        if (holder is ErrorViewHolder) {
            holder.bind("Помогите, я китайский мальчик")
        }
        if (holder is BookmarkViewHolder) {
            holder.bind((state as BookmarksState.Content).data[position])
            holder.itemView.setOnClickListener {
                onClickListener((state as BookmarksState.Content).data[position].malId)
            }
        }
    }

    fun loadContent(list: List<BookmarkModel>) {
        val diffCallback = BookmarkAnimeListDiffUtilCallback((state as BookmarksState.Content).data, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        state = if (list.isNotEmpty()) BookmarksState.Content(list) else BookmarksState.EmptyContent
        diffResult.dispatchUpdatesTo(this)
    }

    fun loadError(message: String?) {
        state = BookmarksState.Error(message ?: "No error message provided.")
        notifyDataSetChanged()
    }
}

sealed class BookmarksState(val itemType: Int) {
    data object Loading : BookmarksState(1)
    data class Error(val msg: String) : BookmarksState(2)
    data class Content(val data: List<BookmarkModel>) : BookmarksState(3)
    data object EmptyContent : BookmarksState(4)
}
