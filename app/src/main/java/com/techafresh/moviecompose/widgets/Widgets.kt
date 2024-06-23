package com.techafresh.moviecompose.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.techafresh.moviecompose.model.Movie
import com.techafresh.moviecompose.model.getMovies


@Composable
fun MovieRow(
    movie: Movie,
    onClick : (String) -> Unit) {

    val expanded = remember{
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onClick(movie.id)
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
//                shape = RectangleShape,
                shadowElevation = 4.dp
            ) {
                Image(painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = movie.images[0])
                        .apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                            transformations(CircleCropTransformation())
                        }).build()
                ),
                    contentDescription = "Movie Poster")
            }
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Text(text = movie.title,
                    style = MaterialTheme.typography.titleMedium)
                Text(text = "Director: ${movie.title}",
                    style = MaterialTheme.typography.bodyMedium)
                Text(text = "Released: ${movie.year}",
                    style = MaterialTheme.typography.bodyMedium)

                AnimatedVisibility(visible = expanded.value) {
                    Column {
                        Text(
                            text = buildAnnotatedString {
                                // String 1
                                withStyle(
                                    style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp
                                    )
                                ){
                                    append("Plot: ")
                                }

                                // String 2
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                ){
                                    append(movie.plot)
                                }
                        },
                            modifier = Modifier.padding(6.dp)
                        )

                        Divider(modifier = Modifier.padding(3.dp))

                        Text(text = "Director : ${movie.director}")
                        Text(text = "Actors : ${movie.actors}")
                        Text(text = "Rating : ${movie.rating}")

                    }
                }

                Icon(
                    imageVector = if (expanded.value) Icons.Default.KeyboardArrowDown
                                  else Icons.Default.KeyboardArrowUp,
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded.value = !expanded.value
                        },
                    tint = Color.DarkGray
                )
            }
        }
    }

}