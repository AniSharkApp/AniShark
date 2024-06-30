package ru.anishark.app.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.anishark.app.R
import ru.anishark.app.databinding.CardSearchCatalogBinding
import ru.anishark.app.domain.model.AnimeModel

class SearchAnimeListAdapter(
    val data: List<AnimeModel>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class SearchAnimeViewHolder(
        val binding: CardSearchCatalogBinding,
        private val onClick: (Int) -> Unit,
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: AnimeModel) {
            binding.root.setOnClickListener {
                onClick(model.malId)
            }
            binding.nameTv.text = model.title
            binding.episodesTv.text = if (model.episodes == null) "0" else "${model.episodes + 1} ep"
            binding.ratingTv.text = "${model.score}"
            binding.descriptionTv.text = model.synopsis
            binding.cardIv.load(model.imageUrl) {
                placeholder(R.drawable.default_anime_catalog_image)
                error(R.drawable.default_anime_catalog_image)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val searchCardViewBinding = CardSearchCatalogBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
            )
        return SearchAnimeViewHolder(searchCardViewBinding, onClick)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if (holder is SearchAnimeViewHolder) {
            holder.bind(data[position])
        }
    }

}
