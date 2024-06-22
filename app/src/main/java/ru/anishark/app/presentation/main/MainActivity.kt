package ru.anishark.app.presentation.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.rxjava3.rxPreferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.anishark.app.R
import ru.anishark.app.databinding.ActivityMainBinding
import ru.anishark.app.presentation.bookmark.fragment.BookmarkFragment
import ru.anishark.app.presentation.catalog.fragment.CatalogFragment
import ru.anishark.app.presentation.home.fragment.HomeFragment

val Context.rxDataStore by rxPreferencesDataStore(
    name = "settings",
    corruptionHandler =
        ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() },
        ),
)

val THEME = intPreferencesKey("theme")

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isDarkTheme = true

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        val themeObservable =
            this.rxDataStore.data().map {
                it[THEME] ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }

        themeObservable
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    isDarkTheme = it == AppCompatDelegate.MODE_NIGHT_YES
                    AppCompatDelegate.setDefaultNightMode(it)
                    Log.d("MyLog", "Tri olega")
                },
                {
                    Log.d("MyLog", it.message ?: "Dwa olega")
                },
            )
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

        loadFragment(HomeFragment::class.java, HOME_FRAGMENT_TAG)
        // TODO: сделать реализацию navigation по человечески
        binding.bottomNavBar.setOnItemSelectedListener { fragment ->
            when (fragment.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment::class.java, HOME_FRAGMENT_TAG)
                    true
                }

                R.id.catalog -> {
                    loadFragment(CatalogFragment::class.java, CATALOG_FRAGMENT_TAG)
                    true
                }

                else -> {
                    loadFragment(BookmarkFragment::class.java, BOOKMARKS_FRAGMENT_TAG)
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
                changeTheme(isDarkTheme)

                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun <T : Fragment> loadFragment(
        fragment: Class<out T>,
        key: String,
    ) {
        val rememberedFragment = supportFragmentManager.findFragmentByTag(key)
        when (rememberedFragment) {
            null -> {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    add(binding.container.id, fragment, null, key)
                    addToBackStack(key)
                }
            }

            else -> {
                supportFragmentManager.popBackStack(key, 0)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun changeTheme(state: Boolean) {
        if (state) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            this.rxDataStore.updateDataAsync {
                val result =
                    it
                        .toMutablePreferences()
                        .apply {
                            this[THEME] = AppCompatDelegate.MODE_NIGHT_NO
                        }.toPreferences()
                Single.just(result)
            }
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            this.rxDataStore.updateDataAsync {
                val result =
                    it
                        .toMutablePreferences()
                        .apply {
                            this[THEME] = AppCompatDelegate.MODE_NIGHT_YES
                        }.toPreferences()
                Single.just(result)
            }
        }
    }

    companion object {
        const val HOME_FRAGMENT_TAG = "HOME"
        const val CATALOG_FRAGMENT_TAG = "CATALOG"
        const val BOOKMARKS_FRAGMENT_TAG = "BOOKMARKS"
    }
}
