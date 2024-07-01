package ru.anishark.app.presentation.main.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.os.bundleOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.rxjava3.rxPreferencesDataStore
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.anishark.app.R
import ru.anishark.app.common.ui.disposeOnDestroy
import ru.anishark.app.databinding.ActivityMainBinding
import ru.anishark.app.presentation.main.adapter.BottomNavigationAdapter
import ru.anishark.app.presentation.main.bus.NavigationEventBus
import javax.inject.Inject
import ru.anishark.app.presentation.search.fragment.SearchFragment

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
    private var disposable = CompositeDisposable()

    @Inject
    lateinit var eventBus: NavigationEventBus

    companion object {
        const val SEARCH = "SEARCH"
    }

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        disposable.disposeOnDestroy(this.lifecycle)
        val themeObservable =
            this.rxDataStore.data().map {
                it[THEME] ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }

        disposable += themeObservable
            .subscribe(
                {
                    isDarkTheme = it == AppCompatDelegate.MODE_NIGHT_YES
                    AppCompatDelegate.setDefaultNightMode(it)
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
        
        disposable +=
            eventBus.subject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewPager.currentItem = it
                })
        viewPager.isUserInputEnabled = false
        viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 ->
                            binding.bottomNavBar.menu
                                .findItem(R.id.home)
                                .setChecked(true)

                        1 ->
                            binding.bottomNavBar.menu
                                .findItem(R.id.catalog)
                                .setChecked(true)

                        2 ->
                            binding.bottomNavBar.menu
                                .findItem(R.id.bookmark)
                                .setChecked(true)
                    }
                }
            },
        )

        binding.bottomNavBar.setOnItemSelectedListener { fragment ->
            when (fragment.itemId) {
                R.id.home -> {
                    eventBus.changeTo(0)
                    true
                }

                R.id.catalog -> {
                    eventBus.changeTo(1)
                    true
                }

                else -> {
                    eventBus.changeTo(2)
                    true
                }
            }
        }

        binding.searchBar.setOnQueryTextListener(object: OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                val arguments = bundleOf(SEARCH to query)

                val transaction = supportFragmentManager.beginTransaction()
                transaction.setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                )
                transaction.replace(binding.searchFragment.id, SearchFragment::class.java, arguments)
                transaction.addToBackStack(SEARCH)
                transaction.commit()

                binding.searchBar.setQuery("", false)
                binding.searchBar.clearFocus()

                return false
            }
        })
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
}
