package com.hitech.pickit.movie.presentation.movie_fav_list.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hitech.pickit.core.presentation.utils.getCategoryFromId
import com.hitech.pickit.ui.theme.PickItTheme

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun CategoryItems(
    modifier: Modifier = Modifier,
    categoryList: List<Int> = emptyList<Int>(),
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(categoryList) { categoryItem ->
            Card(

                shape = RoundedCornerShape(100f),
                elevation = CardDefaults.cardElevation(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.1f))
                    .height(screenHeight * 0.045f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 4.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Text(

                        getCategoryFromId(categoryItem),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                }
            }
        }
    }
}


@Preview
@Composable
private fun CategoryItemsPreview() {
    PickItTheme {
        CategoryItems(
            categoryList = MoviePreview.category
        )
    }

}