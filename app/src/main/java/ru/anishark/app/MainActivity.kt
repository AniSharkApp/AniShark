package ru.anishark.app

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.search.SearchBar
import ru.anishark.app.fragments.BookmarkFragment
import ru.anishark.app.fragments.CatalogFragment
import ru.anishark.app.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.top_app_bar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        loadFragment(HomeFragment())
        // TODO: сделать реализацию navigation по человечески 
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_nav_bar)
        bottomNav.setOnItemSelectedListener { fragment ->
            when(fragment.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.catalog -> {
                    loadFragment(CatalogFragment())
                    true
                }
                else -> {
                    loadFragment(BookmarkFragment())
                    true
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.change_theme_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.change_theme -> {
                // TODO: сделать реализацию смену темы
                Toast.makeText(this, "Помогите, я китайский мальчик", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}

