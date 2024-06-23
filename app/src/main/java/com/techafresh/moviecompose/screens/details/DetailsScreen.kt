package com.techafresh.moviecompose.screens.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.techafresh.moviecompose.model.Movie
import com.techafresh.moviecompose.model.getMovies
import com.techafresh.moviecompose.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navController: NavController, movieID: String?){

    val newMovieList = getMovies().filter { movie ->
        movie.id == movieID
    }

    Scaffold(
//        topBar = {
//            TopAppBar(
//                modifier = Modifier.height(20.dp),
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Magenta),
//                title = {Text(text = "Movies")},
//                navigationIcon = {
//                    Icon(imageVector = Icons.Default.ArrowBack,
//                    contentDescription ="Arrow Back",
//                    modifier = Modifier.clickable {
//                        navController.popBackStack()
//                    })
//                }
//                )
//        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MovieRow(movie = newMovieList.first()){}
                Log.d("MYTAG", "DetailsScreen: /n")
                Divider()
                Text(text = "Movie Images")
                HorizontalScrollableImageView(newMovieList)
            }
        }
    }
}

@Composable
private fun HorizontalScrollableImageView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList.first().images) { image ->
            Card(modifier = Modifier
                .padding(12.dp)
                .size(240.dp),
                elevation = CardDefaults.cardElevation(5.dp)) {
                Image(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Fit)

            }
        }
    }
}

fun getMovie(movieID : String): Movie {
    lateinit var movieQ : Movie
    for (movie in getMovies()){
        if (movie.id == movieID){
            break
        }
        movieQ = movie
    }
    return movieQ
}
