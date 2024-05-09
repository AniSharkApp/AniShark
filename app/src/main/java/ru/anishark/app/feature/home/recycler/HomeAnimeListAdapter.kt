package ru.anishark.app.feature.home.recycler

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import ru.anishark.app.R
import ru.anishark.app.databinding.CardAnimeHomeBinding
import ru.anishark.app.databinding.CardWatchMoreHomeBinding

class AnimeModel(
    val name: String,
    val episodesCount: Int
)

class HomeAnimeListAdapter(
    val data: List<AnimeModel> = MutableList(10) { it -> AnimeModel(name = "Anime ${it + 1}", episodesCount = it) }
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class MoreViewHolder(
        val binding: CardWatchMoreHomeBinding
    ) : RecyclerView.ViewHolder(binding.root)
    class AnimeViewHolder(
        val binding: CardAnimeHomeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(model: AnimeModel) {
            binding.animeNameTv.text = model.name
            binding.episodesTv.text = "${model.episodesCount + 1} эп"
            binding.ratingTv.text = "4.5"
            binding.cardIv.setImageDrawable(AppCompatResources.getDrawable(this.itemView.context, R.drawable.default_anime_image))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == data.size) WATCH_MORE_CARD_TYPE else ANIME_CARD_TYPE
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        if (viewType == WATCH_MORE_CARD_TYPE) {
            val watchMoreBinding = CardWatchMoreHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MoreViewHolder(watchMoreBinding)
        }
        val animeCardViewBinding =
            CardAnimeHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(animeCardViewBinding)
    }

    override fun getItemCount(): Int = data.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AnimeViewHolder) {
            holder.bind(data[position])
        }
    }

    companion object {
        const val ANIME_CARD_TYPE = 1
        const val WATCH_MORE_CARD_TYPE = 2
    }
}