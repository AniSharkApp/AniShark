package ru.anishark.app.presentation.main


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.anishark.app.R
import ru.anishark.app.databinding.ActivityMainBinding
import ru.anishark.app.presentation.bookmark.fragment.BookmarkFragment
import ru.anishark.app.presentation.catalog.fragment.CatalogFragment
import ru.anishark.app.presentation.home.fragment.HomeFragment
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var homeFragment: HomeFragment

    @Inject
    lateinit var catalogFragment: CatalogFragment

    @Inject
    lateinit var bookmarkFragment: BookmarkFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        loadFragment(homeFragment)
        // TODO: сделать реализацию navigation по человечески
        binding.bottomNavBar.setOnItemSelectedListener { fragment ->
            when (fragment.itemId) {
                R.id.home -> {
                    loadFragment(homeFragment)
                    true
                }

                R.id.catalog -> {
                    loadFragment(catalogFragment)
                    true
                }

                else -> {
                    loadFragment(bookmarkFragment)
                    true
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.change_theme_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_theme -> {
                // TODO: сделать реализацию смену темы
                Toast.makeText(this, "Помогите, я китайский мальчик", Toast.LENGTH_SHORT).show()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.container.id, fragment)
        transaction.commit()
    }
}

