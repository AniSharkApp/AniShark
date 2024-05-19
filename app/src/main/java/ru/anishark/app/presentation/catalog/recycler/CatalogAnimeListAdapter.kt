package ru.anishark.app.presentation.catalog.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import ru.anishark.app.R
import ru.anishark.app.databinding.CardAnimeCatalogBinding

class CatalogAnimeListAdapter(val data: List<AnimeModelForCatalog> = MutableList(10) { it -> AnimeModelForCatalog(name = "Берсерк ${it + 1}", description= "Боль, кровь и слёзы. На престол восходит новый правитель. Его слуги, подручные демоны безнаказанно творят бесчинства в городе. Все меняется когда в город, скрытый темнотой ночи, попадает тяжёлый воин. С разнообразным вооружением для самых непредвиденных моментов, в броне, тело его все покрыто шрамами – черный мечник. Его меч настолько огромен, что может сравниться с его лютой злобой к королю и демонам.", episodesCount = 24) })
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class AnimeViewHolder(
        val binding: CardAnimeCatalogBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(model: AnimeModelForCatalog) {
            binding.nameTv.text = model.name
            binding.episodesTv.text = "${model.episodesCount + 1} эп"
            binding.ratingTv.text = "4.5"
            binding.descriptionTv.text = model.description
            binding.cardIv.setImageDrawable(AppCompatResources.getDrawable(this.itemView.context, R.drawable.default_anime_catalog_image))
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val animeCardViewBinding =
            CardAnimeCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(animeCardViewBinding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AnimeViewHolder) {
            holder.bind(data[position])
        }
    }
}