package ru.anishark.app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.anishark.app.R
import ru.anishark.app.databinding.FragmentCatalogBinding
import ru.anishark.app.databinding.FragmentHomeBinding
import ru.anishark.app.feature.catalog.recycler.CatalogAnimeListAdapter
import ru.anishark.app.feature.home.recycler.HomeAnimeListAdapter

class CatalogFragment : Fragment() {
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

    companion object {
        // TODO: Закинуть в Dagger
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}