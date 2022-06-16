package com.shoogarh.newsheadline.utils

sealed class Screen(val route: String) {
    object Home: Screen(route = "home")
    object Detail: Screen(route = "detail")
}
