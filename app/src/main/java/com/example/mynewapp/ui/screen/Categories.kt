package com.example.mynewapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.mynewapp.ui.BottomMenuScreen
import com.example.mynewapp.R
import com.example.mynewapp.network.NewsManager
import com.example.mynewapp.network.models.getArticleCategory

@Composable
fun Categories(onFetchCategory: (String) -> Unit={}, newsManager: NewsManager) {
    val tabsItems = getArticleCategory()
    Column {
        LazyRow{
            items(tabsItems.size){
                val category = tabsItems[it]
                Category(category = category.categoryName, onFetchCategory =onFetchCategory )
            }
        }
    }
}

@Composable
fun Category(category: String, isSelected: Boolean = false, onFetchCategory: (String) -> Unit) {
    val backround =
        if (isSelected) colorResource(id = R.color.purple_200) else colorResource(id = R.color.purple_700)
    androidx.compose.material.Surface(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 16.dp)
            .clickable {
                onFetchCategory(category)
            }, shape = MaterialTheme.shapes.small,
        color = backround
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.body2,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
    }
}