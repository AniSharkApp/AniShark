package ru.anishark.app.presentation.catalog.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.anishark.app.R
import ru.anishark.app.databinding.CardAnimeCatalogBinding
import ru.anishark.domain.model.AnimeModel

class CatalogAnimeListAdapter(
    val data: List<AnimeModel>,
    private val onClick: (Int) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class AnimeViewHolder(
        val binding: CardAnimeCatalogBinding,
        private val onClick: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(model: AnimeModel) {
            binding.root.setOnClickListener {
                onClick(model.malId)
            }
            binding.nameTv.text = model.title
            binding.episodesTv.text = if (model.episodes == null) "ON" else "${model.episodes!! + 1} ep"
            binding.ratingTv.text = "${model.score}"
            binding.descriptionTv.text = model.synopsis
            binding.cardIv.load(model.imageUrl) {
                placeholder(R.drawable.default_anime_catalog_image)
                error(R.drawable.default_anime_catalog_image)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val animeCardViewBinding =
            CardAnimeCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(animeCardViewBinding, onClick)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        if (holder is AnimeViewHolder) {
            holder.bind(data[position])
        }
    }
}
