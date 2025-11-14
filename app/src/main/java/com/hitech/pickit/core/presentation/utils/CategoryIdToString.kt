package com.hitech.pickit.core.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.hitech.pickit.R

@Composable
fun getCategoryFromId(id: Int): String {
    return when (id) {
        28 -> stringResource(R.string.cat_action)
        12 -> stringResource(R.string.cat_adventure)
        16 -> stringResource(R.string.cat_animation)
        35 -> stringResource(R.string.cat_comedy)
        80 -> stringResource(R.string.cat_crime)
        99 -> stringResource(R.string.cat_documentary)
        18 -> stringResource(R.string.cat_drama)
        10751 -> stringResource(R.string.cat_family)
        14 -> stringResource(R.string.cat_fantasy)
        36 -> stringResource(R.string.cat_history)
        27 -> stringResource(R.string.cat_horror)
        10402 -> stringResource(R.string.cat_music)
        9648 -> stringResource(R.string.cat_mystery)
        10749 -> stringResource(R.string.cat_romance)
        878 -> stringResource(R.string.cat_scifi)
        10770 -> stringResource(R.string.cat_tv_movie)
        53 -> stringResource(R.string.cat_thriller)
        10752 -> stringResource(R.string.cat_war)
        37 -> stringResource(R.string.cat_western)
        else -> stringResource(R.string.cat_unknown)


    }
}