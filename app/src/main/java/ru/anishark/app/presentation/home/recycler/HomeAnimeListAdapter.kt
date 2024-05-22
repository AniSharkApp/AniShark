package ru.anishark.app.presentation.home.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import ru.anishark.app.R
import ru.anishark.app.databinding.CardAnimeHomeBinding
import ru.anishark.app.databinding.CardWatchMoreHomeBinding
import ru.anishark.app.databinding.LayoutErrorBinding
import ru.anishark.app.databinding.LayoutLoadingBinding
import ru.anishark.app.domain.model.AnimeModel


class HomeAnimeListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data: MutableList<AnimeModel> = mutableListOf()
    private var errorMessage: String? = null

    class MoreViewHolder(
        val binding: CardWatchMoreHomeBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class AnimeViewHolder(
        val binding: CardAnimeHomeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(model: AnimeModel) {
            binding.animeNameTv.text = model.title
            binding.episodesTv.text = "${model.episodes} эп"
            binding.ratingTv.text = "${model.score / 2.0}"
            binding.cardIv.setImageDrawable(
                AppCompatResources.getDrawable(
                    this.itemView.context,
                    R.drawable.default_anime_image
                )
            )
        }
    }

    class LoadingViewHolder(
        val binding: LayoutLoadingBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class ErrorViewHolder(
        val binding: LayoutErrorBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: String) {
            with(binding) {
                errorTv.text = message
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (errorMessage != null) return ERROR_TYPE
        if (data.size == 0) return LOADING_TYPE
        return if (position == data.size) WATCH_MORE_CARD_TYPE else ANIME_CARD_TYPE
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            LOADING_TYPE -> {
                val loadingBinding = LayoutLoadingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                LoadingViewHolder(loadingBinding)
            }
            ERROR_TYPE -> {
                val errorBinding = LayoutErrorBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ErrorViewHolder(errorBinding)
            }
            WATCH_MORE_CARD_TYPE -> {
                val watchMoreBinding = CardWatchMoreHomeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                MoreViewHolder(watchMoreBinding)
            }
            else -> {
                val animeCardViewBinding = CardAnimeHomeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AnimeViewHolder(animeCardViewBinding)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun dataLoaded(data: List<AnimeModel>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (data.size == 0) return 1
        return data.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AnimeViewHolder) {
            holder.bind(data[position])
        }
        if (holder is ErrorViewHolder) {
            holder.bind(errorMessage ?: "No error message provided.")
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadError(message: String?) {
        errorMessage = message ?: "No error message provided."
        notifyDataSetChanged()
    }

    companion object {
        const val ANIME_CARD_TYPE = 1
        const val WATCH_MORE_CARD_TYPE = 2
        const val LOADING_TYPE = 3
        const val ERROR_TYPE = 4
    }
}