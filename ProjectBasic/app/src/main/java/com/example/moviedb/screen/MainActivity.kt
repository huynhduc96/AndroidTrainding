package com.example.moviedb.screen

import android.os.Bundle
import com.example.moviedb.R
import com.example.moviedb.screen.base.BaseActivity
import com.example.moviedb.screen.mainlayout.ContainerFragment

class MainActivity : BaseActivity() {

    override fun initComponent(saveInstantState: Bundle?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrameLayout, ContainerFragment())
            .commit()
    }

    override fun getLayout() = R.layout.activity_main
}
