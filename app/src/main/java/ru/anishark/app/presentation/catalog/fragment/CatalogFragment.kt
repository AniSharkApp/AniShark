package ru.anishark.app.presentation.catalog.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.anishark.app.databinding.FragmentCatalogBinding
import ru.anishark.app.presentation.catalog.recycler.CatalogAnimeListAdapter
import ru.anishark.app.presentation.catalog.viewmodel.CatalogViewModel
import ru.anishark.app.presentation.filter.activity.FilterActivity

@AndroidEntryPoint
class CatalogFragment : Fragment() {
    private val vm: CatalogViewModel by viewModels()

    private lateinit var binding: FragmentCatalogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatalogBinding.inflate(inflater, container, false)
        with(binding) {
            catalogRv.adapter = CatalogAnimeListAdapter()
            catalogRv.layoutManager = LinearLayoutManager(catalogRv.context)
            filterButton.setOnClickListener({
                val intent = Intent(binding.filterButton.context, FilterActivity::class.java)
                startActivity(intent)
            })
        }
        return binding.root
    }
}