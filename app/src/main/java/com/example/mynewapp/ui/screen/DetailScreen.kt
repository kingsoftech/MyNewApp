package com.example.mynewapp.ui.screen

import android.net.wifi.ScanResult
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mynewapp.MockData
import com.example.mynewapp.MockData.getTimeAgo
import com.example.mynewapp.NewsData
import com.example.mynewapp.R

import java.security.AllPermission

@Composable
fun DetailScreen(navController: NavController, newsData: NewsData, scrollState: ScrollState) {


    Scaffold(topBar = { DetailTopBar(onBackPressed = {navController.popBackStack()})}) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Detail screen", fontWeight = FontWeight.SemiBold)
            Image(painter = painterResource(id = newsData.image), contentDescription = "")
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                InfoWithIcon(icon = Icons.Default.Edit, info = newsData.author)
                InfoWithIcon(icon = Icons.Default.DateRange, info = MockData.stringToDate(newsData.publishedAt).getTimeAgo())
            }
            Text(text = newsData.title, fontWeight = FontWeight.Bold)
            Text(text = newsData.description, modifier = Modifier.padding(top = 16.dp))
        }
    }

}
@Composable
fun DetailTopBar(onBackPressed:()-> Unit = {}){
    TopAppBar(title = {Text(text="Detail Screen", fontWeight = FontWeight.SemiBold)}, navigationIcon = {
        IconButton(onClick = { onBackPressed()}) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
        }
    })
}
@Composable

fun InfoWithIcon(icon: ImageVector, info:String){
    Row {
        Icon(imageVector = icon, contentDescription = "Author",
        modifier = Modifier.padding(end=8.dp), colorResource(id = R.color.purple_200))
        Text(text = info)
    }


}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        rememberNavController(), NewsData(
            8,
            author = "CBSBoston.com Staff",
            title = "Principal Beaten Unconscious At Dorchester School; Classes Canceled Thursday - CBS Boston",
            description = "Principal Patricia Lampron and another employee were assaulted at Henderson Upper Campus during dismissal on Wednesday.",
            publishedAt = "2021-11-04T01:55:00Z"
        ), rememberScrollState()
    )
}