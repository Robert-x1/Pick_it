package com.hitech.pickit.movie.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hitech.pickit.R
import com.hitech.pickit.ui.theme.PickItTheme

@Composable
fun ProfileScreenGradient(
    modifier: Modifier = Modifier,
    imageUrl: String?
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        if (imageUrl != null) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            // 2. Else, fallback to local Joker resource
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.default_user_img),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )
        }
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.surface
                        ),
                        startY = 300f,
                        endY = 900f
                    )
                )
        )
    }
}

@Preview
@Composable
private fun ProfileScreenGradientPreview() {
    PickItTheme {
        ProfileScreenGradient(
            imageUrl = null
        )
    }
}
