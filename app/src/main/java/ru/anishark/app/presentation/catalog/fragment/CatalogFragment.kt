package ru.anishark.app.presentation.catalog.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.anishark.app.databinding.FragmentCatalogBinding
import ru.anishark.app.presentation.catalog.recycler.CatalogAnimeListAdapter
import ru.anishark.app.presentation.catalog.viewmodel.CatalogViewModel
import ru.anishark.app.presentation.filter.activity.FilterActivity
const val FIRST_NAME_KEY = "fnk"
const val SECOND_NAME_KEY = "snk"
const val THIRD_NAME_KEY = "tnk"
@AndroidEntryPoint
class CatalogFragment : Fragment() {
    private val vm: CatalogViewModel by viewModels()
    private lateinit var binding: FragmentCatalogBinding

    private lateinit var startForResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startForResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.let { intent ->
                    val firstNameList = intent.getStringArrayListExtra(FIRST_NAME_KEY)
                    val secondNameList = intent.getStringArrayListExtra(SECOND_NAME_KEY)
                    val thirdNameList = intent.getStringArrayListExtra(THIRD_NAME_KEY)

                    Toast.makeText(context, "Received data: ${firstNameList?.joinToString()} ${secondNameList?.joinToString()} ${thirdNameList?.joinToString()}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatalogBinding.inflate(inflater, container, false)
        with(binding) {
            catalogRv.adapter = CatalogAnimeListAdapter()
            catalogRv.layoutManager = LinearLayoutManager(catalogRv.context)
            filterButton.setOnClickListener {
                val intent = Intent(filterButton.context, FilterActivity::class.java)
                startForResultLauncher.launch(intent)
            }
        }
        return binding.root
    }
}