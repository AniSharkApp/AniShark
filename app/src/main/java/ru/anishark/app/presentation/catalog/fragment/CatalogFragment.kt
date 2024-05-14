package ru.anishark.app.presentation.catalog.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.anishark.app.databinding.FragmentCatalogBinding
import ru.anishark.app.presentation.catalog.recycler.CatalogAnimeListAdapter
import ru.anishark.app.presentation.home.fragment.HomeFragment
import javax.inject.Inject

@AndroidEntryPoint
class CatalogFragment @Inject constructor(): Fragment() {
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
        }
        return binding.root
    }
}