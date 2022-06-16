package com.shoogarh.newsheadline.screens

import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.shoogarh.newsheadline.widgets.ShowProgressBar

@Composable
fun ArticleWebview(navController: NavHostController, url: String?) {

    var isLoading = remember {
        mutableStateOf(false)
    }
    Column {
        if (isLoading.value){
            ShowProgressBar()
        }
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                            isLoading.value = true
                        }

                        override fun onPageFinished(view: WebView, url: String) {
                            isLoading.value = false
                        }
                    }
                    settings.javaScriptEnabled = true
                }
            }, update = { webView ->
                if (url != null) {
                    webView.loadUrl(url)
                }
            })
    }
}