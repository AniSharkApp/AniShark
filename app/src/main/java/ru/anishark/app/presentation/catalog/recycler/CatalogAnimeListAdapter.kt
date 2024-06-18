package ru.anishark.app.presentation.catalog.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import ru.anishark.app.R
import ru.anishark.app.databinding.CardAnimeCatalogBinding
import coil.load

class CatalogAnimeListAdapter(val data: List<AnimeModelForCatalog>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class AnimeViewHolder(
        val binding: CardAnimeCatalogBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(model: AnimeModelForCatalog) {
            binding.nameTv.text = model.name
            binding.episodesTv.text = "${model.episodesCount + 1} ep"
            binding.ratingTv.text = "${model.rating}"
            binding.descriptionTv.text = model.description
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
        return AnimeViewHolder(animeCardViewBinding)
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
