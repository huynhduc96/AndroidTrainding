package com.example.moviedb.screen

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.moviedb.screen.favorite.FavoriteFragment
import com.example.moviedb.screen.home.HomeFragment

class TabAdapter(fm: FragmentManager, context: Context) : FragmentStatePagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {

        return when(p0) {
            0 -> HomeFragment()
            1 -> FavoriteFragment()
            else -> HomeFragment()
        }

    }

    override fun getCount(): Int {
        return 2
    }

}