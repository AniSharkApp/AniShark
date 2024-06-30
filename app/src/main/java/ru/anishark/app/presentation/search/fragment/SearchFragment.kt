package ru.anishark.app.presentation.search.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.anishark.app.common.ui.disposeOnDestroy
import ru.anishark.app.databinding.FragmentSearchBinding
import ru.anishark.app.presentation.anime.AnimeScreenActivity
import ru.anishark.app.presentation.search.viewmodel.SearchViewModel

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var disposable = CompositeDisposable()

    private val vm: SearchViewModel by viewModels()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private fun startAnimeActivity(malId: Int) {
        val intent = Intent(this@SearchFragment.context, AnimeScreenActivity::class.java)
        intent.putExtra("malId", malId)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposable.disposeOnDestroy(this.lifecycle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container,false)

        binding.fragmentSearch.setOnClickListener {
            // TODO: это заглушка
            Log.d("MyLog", "Fragment clicked")
        }

        val mainActivity = requireActivity()
        mainActivity.onBackPressedDispatcher.addCallback(this) {
            mainActivity.supportFragmentManager.popBackStackImmediate()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
