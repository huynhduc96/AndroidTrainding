/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.moviedb.screen

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.moviedb.R
import com.example.moviedb.screen.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity() {
    override fun initComponent(saveInstantState: Bundle?) {
        this.bottomNavigation.setOnNavigationItemSelectedListener(selectedListener)
        setSupportActionBar(toolbar)

        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                when (position) {
                    0 -> bottomNavigation.menu.getItem(0).isChecked = true
                    1 -> bottomNavigation.menu.getItem(1).isChecked = true
                }
            }

            override fun onPageSelected(position: Int) {}
        })

        val tabAdapter = TabAdapter(supportFragmentManager, this)

        viewPager.adapter = tabAdapter
    }

    override fun getLayout(): Int = R.layout.activity_main

    //Setting NavigationBottom

    private val selectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    viewPager.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.favorite -> {
                    viewPager.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }
            }
            return@OnNavigationItemSelectedListener false
        }
}
