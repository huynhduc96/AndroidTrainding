package com.example.moviedb.screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviedb.screen.favorite.FavoriteFragment
import com.example.moviedb.screen.home.HomeFragment
import com.example.moviedb.utils.annotation.MainPage

class MainViewPageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(postion: Int): Fragment {
        return when (postion) {
            MainPage.HOME_PAGE-> HomeFragment.newInstance()
            MainPage.FAVORITE_PAGE -> FavoriteFragment.newInstance()
            else -> HomeFragment()
        }
    }

    override fun getCount() = TAB_COUNT

    companion object {
        private const val TAB_COUNT = 2
    }
}
