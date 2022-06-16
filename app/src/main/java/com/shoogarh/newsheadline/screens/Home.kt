package com.shoogarh.newsheadline.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shoogarh.newsheadline.models.Article
import com.shoogarh.newsheadline.services.RestApiService
import com.shoogarh.newsheadline.utils.DEFAULT_IMAGE
import com.shoogarh.newsheadline.utils.LoadPicture
import com.shoogarh.newsheadline.utils.Screen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ArticlesContent(navController: NavHostController) {
    var articles = remember {
        mutableStateListOf<Article>()
    }
    if (articles.isEmpty()){
        getData {
            articles.addAll(it)
        }
    }
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Newsfeed") }) },
        content = { pad ->
            Column {
                if (articles.isEmpty()){
                    ShowProgressBar()
                }
                LazyColumn(
                    modifier = Modifier.padding(pad)
                ) {
                    itemsIndexed(articles) { _, item ->
                        Column {
                            val image = item.image?.let {
                                LoadPicture(
                                    url = it,
                                    defaultImage = DEFAULT_IMAGE
                                ).value
                            }
                            image?.let {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .border(2.dp, Color.Black)
                                        .clickable(onClick = {
                                            navController.navigate(
                                                "${Screen.Detail.route}/${
                                                    URLEncoder.encode(
                                                        item.url,
                                                        StandardCharsets.UTF_8.toString()
                                                    )
                                                }"
                                            )
                                        })
                                ) {
                                    Image(
                                        bitmap = it.asImageBitmap(),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(225.dp),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = "News Image",
                                    )
                                    Surface(
                                        color = Color.Black.copy(alpha = 0.6f),
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .height(225.dp)
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.Bottom,
                                            horizontalAlignment = Alignment.Start,
                                        ) {
                                            Text(
                                                text = "${item.title}",
                                                fontSize = 18.sp,
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = 8.dp, horizontal = 16.dp)
                                            )
                                            Text(
                                                text = "${item.author ?: ""}",
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = 8.dp, horizontal = 16.dp)
                                            )
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    )
}

private fun getData(callback: (List<Article>) -> Unit) {
    RestApiService().getArticles {
        if (it?.articles != null) {
            callback(it.articles)
        }
    }
}

@Composable
fun ShowProgressBar() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}