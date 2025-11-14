package com.hitech.pickit.movie.presentation.cinemas_screen

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.hitech.pickit.R
import com.hitech.pickit.movie.presentation.cinemas_screen.components.CinemaSelectorUI
import com.hitech.pickit.movie.presentation.cinemas_screen.components.MoviesLazyRow
import com.hitech.pickit.movie.presentation.cinemas_screen.components.WeekCalendarRow
import com.hitech.pickit.movie.presentation.movie_fav_list.components.MoviePreview
import com.hitech.pickit.movie.utili.MovieState
import com.hitech.pickit.ui.theme.PickItTheme

@Composable
fun CinemasScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CinemaSelectorUI()
        Spacer(modifier = Modifier.height(16.dp))
        WeekCalendarRow()
        Spacer(modifier = Modifier.height(16.dp))
        MoviesLazyRow(
            state = MovieState(
                isLoading = false,
                movies = (1..10).map { MoviePreview.copy(id = it.toString()) },
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(stringResource(R.string.book_set))
        }


    }


}

@PreviewLightDark
@Composable
private fun CinemasPreview() {
    PickItTheme {
        CinemasScreen()
    }

}