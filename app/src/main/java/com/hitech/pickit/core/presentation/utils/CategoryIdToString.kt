package com.hitech.pickit.core.presentation.utils

import androidx.annotation.StringRes
import com.hitech.pickit.R

@StringRes
fun getCategoryFromId(id: Int): Int {
    return when (id) {
        28 -> R.string.cat_action
        12 -> R.string.cat_adventure
        16 -> R.string.cat_animation
        35 -> R.string.cat_comedy
        80 -> R.string.cat_crime
        99 -> R.string.cat_documentary
        18 -> R.string.cat_drama
        10751 -> R.string.cat_family
        14 -> R.string.cat_fantasy
        36 -> R.string.cat_history
        27 -> R.string.cat_horror
        10402 -> R.string.cat_music
        9648 -> R.string.cat_mystery
        10749 -> R.string.cat_romance
        878 -> R.string.cat_scifi
        10770 -> R.string.cat_tv_movie
        53 -> R.string.cat_thriller
        10752 -> R.string.cat_war
        37 -> R.string.cat_western
        else -> R.string.cat_unknown
    }
}