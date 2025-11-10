package com.hitech.pickit.movie.presentation.seachScreen.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hitech.pickit.R
import com.hitech.pickit.movie.utili.MoviePoster
import com.hitech.pickit.movie.utili.SearchData
import com.hitech.pickit.ui.MoviePosterCard

@Composable
fun RecentSearches(
    modifier: Modifier = Modifier,
//    recentSearches: List<MoviePoster>,
//    onItemClick: (String) -> Unit
) {

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.recent_searches),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )


    }
}

@PreviewLightDark
@Composable
private fun test() {
    RecentSearches()

}

