package ru.anishark.app.presentation.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.anishark.app.databinding.FragmentHomeBinding
import ru.anishark.app.presentation.home.recycler.HomeAnimeListAdapter
import ru.anishark.app.presentation.home.viewmodel.HomeViewModel
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor() : Fragment() {
    private val vm: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        with(binding) {
            topsRv.adapter = HomeAnimeListAdapter()
            topsRv.layoutManager = LinearLayoutManager(
                topsRv.context, LinearLayoutManager.HORIZONTAL, false
            )
            actualRv.adapter = HomeAnimeListAdapter()
            actualRv.layoutManager = LinearLayoutManager(
                actualRv.context, LinearLayoutManager.HORIZONTAL, false
            )
        }
        return binding.root
    }
}