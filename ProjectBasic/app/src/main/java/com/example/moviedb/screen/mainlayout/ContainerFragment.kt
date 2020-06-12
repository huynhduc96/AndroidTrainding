package com.example.moviedb.screen.mainlayout

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.moviedb.R
import com.example.moviedb.screen.MainActivity
import com.example.moviedb.screen.MainViewPageAdapter
import com.example.moviedb.screen.home.HomeFragment
import com.example.moviedb.utils.annotation.MainPage
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_container.*
import kotlinx.android.synthetic.main.toolbar.view.*

class ContainerFragment : Fragment(), ViewPager.OnPageChangeListener {

    //Setting NavigationBottom
    private val selectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    viewPager.currentItem = MainPage.HOME_PAGE
                    return@OnNavigationItemSelectedListener true
                }
                R.id.favorite -> {
                    viewPager.currentItem = MainPage.FAVORITE_PAGE
                    return@OnNavigationItemSelectedListener true
                }
            }
            return@OnNavigationItemSelectedListener false
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar()
        activity?.run {
            viewPager.adapter = MainViewPageAdapter(childFragmentManager)
        }
        bottomNavigation.setOnNavigationItemSelectedListener(selectedListener)
        viewPager.addOnPageChangeListener(this)
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        when (position) {
            MainPage.HOME_PAGE -> bottomNavigation.menu.getItem(
                MainPage.HOME_PAGE
            ).isChecked = true
            MainPage.FAVORITE_PAGE -> bottomNavigation.menu.getItem(
                MainPage.FAVORITE_PAGE
            ).isChecked = true
        }
    }

    override fun onPageSelected(position: Int) {}

    private fun initToolBar() {
        view?.toolbar?.let {
            (activity as? MainActivity)?.run { setSupportActionBar(it) }
        }
    }

    companion object {
        fun newInstance() = ContainerFragment()
    }
}
