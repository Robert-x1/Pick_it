package com.hitech.pickit.movie.presentation.movie_fav_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hitech.pickit.R
import com.hitech.pickit.movie.domain.Movie
import com.hitech.pickit.movie.presentation.models.MovieUi
import com.hitech.pickit.movie.presentation.models.toMovieUi
import com.hitech.pickit.movie.utili.StarsRate
import com.hitech.pickit.ui.theme.PickItTheme


@Composable
fun MovieListItem(
    movieUi: MovieUi,
    onclick: () -> Unit,
    screenHeight: Dp,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .background(Color.Transparent)
            .padding(15.dp)
            .padding(top = 15.dp),
        elevation = CardDefaults.cardElevation(20.dp),
        shape = RoundedCornerShape(80f),
    ) {
        Column(
            modifier = Modifier
                .height(screenHeight)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(horizontal = 10.dp)
                    .padding(vertical = 12.dp)
                    .height(screenHeight * 0.5f),
                shape = RoundedCornerShape(80f)

            ) {
                Image(
                    painter = painterResource(R.drawable.joker),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize(),
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillBounds,
                )
            }
            Text(
                movieUi.name,
                style = MaterialTheme.typography.displayMedium
            )



            Row(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .padding(top = 10.dp)
            ) {
                CategoryItems(
                    categoryList = movieUi.category,
                    modifier = Modifier
                        .height(screenHeight * 0.045f)
                        .fillMaxWidth()
                )
            }



            StarsRate(
                rate = movieUi.rate,
                starSizeDp = 25,
                modifier = Modifier
                    .height(50.dp)
                    .padding(top = 10.dp)
            )


            Text(
                "25.4 $",
                fontSize = 50.sp,
                style = MaterialTheme.typography.labelLarge
            )


            Button(
                onClick = onclick,
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(10.dp)
            ) {
                Text(
                    "Watch Now",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}


@Preview
@Composable
private fun MovieItemPreview() {
    PickItTheme {
        MovieListItem(
            movieUi = MoviePreview,
            onclick = { },
            screenHeight = 900.dp,
            modifier = Modifier.background(MaterialTheme.colorScheme.surface)
        )
    }

}

internal val MoviePreview = Movie(
    id = "1",
    name = "Joker",
    rate = 4.5,
    category = listOf(35, 16, 28, 27, 878, 10770),
    picture = "TODO()",
    overview = "null",
    releaseDate = "TODO()",
).toMovieUi()