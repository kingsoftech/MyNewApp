package com.example.mynewapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mynewapp.MockData
import com.example.mynewapp.MockData.getTimeAgo
import com.example.mynewapp.NewsData
import com.example.mynewapp.R
import com.example.mynewapp.network.models.TopNewsArticle
import com.example.mynewapp.ui.Navigation
import com.skydoves.landscapist.coil.CoilImage


@Composable

fun TopNews(navController: NavController, article:List<TopNewsArticle>) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = " Top News", fontWeight = FontWeight.SemiBold)
        LazyColumn{
            items(article.size){
                    index
                ->
                TopNewsItem(newsData = article[index],
                onNewsClicked = {
                    navController.navigate("Detail/$index")
                })
            } }
    }
}

@Composable
fun TopNewsItem(newsData: TopNewsArticle?,onNewsClicked: ()->Unit = {}) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .padding(8.dp)
            .clickable {
                onNewsClicked()
            }
    ) {
        com.skydoves.landscapist.coil.CoilImage(
            imageModel = newsData!!.urlToImage,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            error = ImageBitmap.imageResource(id = R.drawable.breaking_news),
            placeHolder = ImageBitmap.imageResource(id = R.drawable.breaking_news)
        )
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(top = 16.dp, start = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = MockData.stringToDate(newsData.publishedAt!!).getTimeAgo(), color = Color.White, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(80.dp))
            Text(text = newsData.title!!, color = Color.White, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    TopNewsItem(TopNewsArticle(
        author = "CBSBoston.com Staff",
        title = "Principal Beaten Unconscious At Dorchester School; Classes Canceled Thursday - CBS Boston",
        description = "Principal Patricia Lampron and another employee were assaulted at Henderson Upper Campus during dismissal on Wednesday.",
        publishedAt = "2021-11-04T01:55:00Z"
    ))
}