package com.hitech.pickit.movie.presentation.credit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.hitech.pickit.R
import com.hitech.pickit.movie.data.mappers.asActor
import com.hitech.pickit.movie.domain.model.TMDbItemDetails
import com.hitech.pickit.movie.presentation.detail_screen.BaseDetailViewModel
import com.hitech.pickit.movie.presentation.models.Actor
import com.hitech.pickit.movie.presentation.models.Credit
import com.hitech.pickit.movie.presentation.models.TMDbItem
import com.hitech.pickit.movie.utili.UiState

@Composable
fun CastScreen(
    viewModel: BaseDetailViewModel<out TMDbItemDetails, out TMDbItem>,
    onUpPress: () -> Unit,
    onPersonClick: (Credit) -> Unit = {}
) {

    val state by viewModel.stateFlow.collectAsState()
    val castList: List<Actor> = when (val uiState = state) {
        is UiState.Success -> {
            uiState.data.cast.map { it.asActor() }
        }

        else -> emptyList()
    }

    CreditScreen(
        resourceId = R.string.cast,
        upPress = onUpPress,
        onPersonClicked = onPersonClick,
        items = castList
    )
}