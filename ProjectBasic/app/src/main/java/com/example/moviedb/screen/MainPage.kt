package com.example.moviedb.screen

import androidx.annotation.IntDef

@IntDef(MainPage.HOME_PAGE,
    MainPage.FAVORITE_PAGE)

annotation class MainPage {
    companion object {
        const val HOME_PAGE = 0
        const val FAVORITE_PAGE = 1
    }
}
