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
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.anishark.app.R
import ru.anishark.app.databinding.ActivityMainBinding
import ru.anishark.app.presentation.BottomNavigationAdapter

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

        val viewPager = binding.container
        viewPager.adapter = BottomNavigationAdapter(this)
        viewPager.currentItem = 0

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position) {
                    0 -> binding.bottomNavBar.menu.findItem(R.id.home).setChecked(true)
                    1 -> binding.bottomNavBar.menu.findItem(R.id.catalog).setChecked(true)
                    2 -> binding.bottomNavBar.menu.findItem(R.id.bookmark).setChecked(true)
                }
            }
        })


        binding.bottomNavBar.setOnItemSelectedListener { fragment ->
            when (fragment.itemId) {
                R.id.home -> {
                    viewPager.currentItem = 0
                    true
                }

                R.id.catalog -> {
                    viewPager.currentItem = 1
                    true
                }

                else -> {
                    viewPager.currentItem = 2
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
                changeTheme(isDarkTheme)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
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


//    private fun <T : Fragment> loadFragment(fragment: Class<out T>) {
//        val transaction = supportFragmentManager.beginTransaction()
//        // TODO: Переделать на человеческий
//        // Слева направо
//        transaction.setCustomAnimations(
//            R.anim.enter_from_right,
//            R.anim.exit_to_left,
//            R.anim.enter_from_left,
//            R.anim.exit_to_right
//        )
////        // Справа налево
////        transaction.setCustomAnimations(
////            R.anim.enter_from_left,
////            R.anim.exit_to_right,
////            R.anim.enter_from_right,
////            R.anim.exit_to_left
////        )
//        transaction.replace(binding.container.id, fragment, null)
//        transaction.commit()
//    }
}
