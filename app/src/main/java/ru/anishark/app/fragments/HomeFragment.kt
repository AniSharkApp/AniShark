package ru.anishark.app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ru.anishark.app.R
import ru.anishark.app.databinding.FragmentHomeBinding
import ru.anishark.app.feature.home.recycler.HomeAnimeListAdapter

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        with(binding) {
            topsRv.adapter = HomeAnimeListAdapter()
            topsRv.layoutManager = LinearLayoutManager(
                this@HomeFragment.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            actualRv.adapter = HomeAnimeListAdapter()
            actualRv.layoutManager = LinearLayoutManager(
                this@HomeFragment.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
        return binding.root
    }

    companion object {
        // TODO: Закинуть в Dagger
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}