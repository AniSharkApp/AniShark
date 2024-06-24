package ru.anishark.app.presentation.home.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.anishark.app.R
import ru.anishark.app.databinding.CardAnimeHomeBinding
import ru.anishark.app.databinding.CardWatchMoreHomeBinding
import ru.anishark.app.databinding.LayoutErrorBinding
import ru.anishark.app.databinding.LayoutLoadingBinding
import ru.anishark.app.domain.model.AnimeModel
import java.util.Locale

class HomeAnimeListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data: MutableList<AnimeModel> = mutableListOf()
    private var errorMessage: String? = null

    class MoreViewHolder(
        val binding: CardWatchMoreHomeBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    class AnimeViewHolder(
        val binding: CardAnimeHomeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: AnimeModel) {
            val episodesNumber =
                with(binding.root.context) {
                    if (model.episodes != null) {
                        getString(R.string.episodes, model.episodes)
                    } else {
                        getString(R.string.ongoing)
                    }
                }
            binding.animeNameTv.text = model.title
            binding.episodesTv.text = episodesNumber
            binding.ratingTv.text = String.format(Locale.ROOT, "%.2f", model.score)
            binding.cardIv.load(model.imageUrl)
        }
    }

    class LoadingViewHolder(
        val binding: LayoutLoadingBinding,
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

    override fun getItemViewType(position: Int): Int {
        if (errorMessage != null) return ERROR_TYPE
        if (data.size == 0) return LOADING_TYPE
        return if (position == data.size) WATCH_MORE_CARD_TYPE else ANIME_CARD_TYPE
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder =
        when (viewType) {
            LOADING_TYPE -> {
                val loadingBinding =
                    LayoutLoadingBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                LoadingViewHolder(loadingBinding)
            }

            ERROR_TYPE -> {
                val errorBinding =
                    LayoutErrorBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                ErrorViewHolder(errorBinding)
            }

            WATCH_MORE_CARD_TYPE -> {
                val watchMoreBinding =
                    CardWatchMoreHomeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                MoreViewHolder(watchMoreBinding)
            }

            else -> {
                val animeCardViewBinding =
                    CardAnimeHomeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false,
                    )
                AnimeViewHolder(animeCardViewBinding)
            }
        }

    fun dataLoaded(data: List<AnimeModel>) {
        val diffCallback = HomeAnimeListDiffUtilCallback(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.data.clear()
        this.data.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        if (data.size == 0) return 1
        return data.size + 1
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        if (holder is AnimeViewHolder) {
            holder.bind(data[position])
        }
        if (holder is ErrorViewHolder) {
            holder.bind(errorMessage ?: "No error message provided.")
        }
    }

    fun loadError(message: String?) {
        errorMessage = message ?: "No error message provided."
        val diffCallback = HomeAnimeListDiffUtilCallback(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.data.clear()
        diffResult.dispatchUpdatesTo(this)
    }

    companion object {
        const val ANIME_CARD_TYPE = 1
        const val WATCH_MORE_CARD_TYPE = 2
        const val LOADING_TYPE = 3
        const val ERROR_TYPE = 4
    }
}
