package ru.anishark.app.presentation.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.anishark.app.presentation.bookmark.fragment.BookmarkFragment
import ru.anishark.app.presentation.catalog.fragment.CatalogFragment
import ru.anishark.app.presentation.home.fragment.HomeFragment

class BottomNavigationAdapter(
    activity: FragmentActivity,
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        when (position) {

            0 -> HomeFragment()
            1 -> CatalogFragment()
            2 -> BookmarkFragment()
            else -> throw IllegalStateException("Господи спаси")
        }
}
