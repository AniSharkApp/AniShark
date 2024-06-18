package ru.anishark.app.presentation.anime

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.anishark.app.databinding.ActivityAnimeBinding
import ru.anishark.app.presentation.anime.viewmodel.AnimeViewModel

@AndroidEntryPoint
class AnimeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeBinding

    private val vm: AnimeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let { screenData ->
            val malId = screenData.getInt("malId") ?: -1
            // TODO: убрать тост
            Toast.makeText(this, malId.toString(), Toast.LENGTH_SHORT).show()
        }
        with(binding) {
            bookmarksScrollView.smoothScrollTo(0, 0)
            icAnimeScreenBack.setOnClickListener {
                finish()
            }
        }
    }
}
