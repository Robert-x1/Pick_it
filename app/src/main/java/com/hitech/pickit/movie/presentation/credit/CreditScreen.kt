package com.hitech.pickit.movie.presentation.credit

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.hitech.pickit.movie.presentation.models.Actor
import com.hitech.pickit.movie.presentation.models.Credit
import com.hitech.pickit.movie.utili.Spacing
import com.hitech.pickit.movie.utili.TMDbSpacer
import com.hitech.pickit.movie.utili.components.PersonCard
import com.hitech.pickit.movie.utili.fullSpanGridItem
import com.hitech.pickit.movie.utili.navigationBarPadding

@Composable
fun CreditScreen(
    @StringRes resourceId: Int,
    upPress: () -> Unit,
    onPersonClicked: (person: Credit) -> Unit,
    items: List<Actor>,
    testPainter: Painter? = null,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(Spacing.mega_120),
        contentPadding =
        PaddingValues(
            start = Spacing.smallMedium_6,
            end = Spacing.smallMedium_6,
            top = Spacing.smallMedium_6,
            bottom = navigationBarPadding().plus(Spacing.medium_8),
        ),
        content = {
            fullSpanGridItem {
                TMDbSpacer()
            }

            items(items.size) { index ->
                PersonCard(
                    person = items[index],
                    onPersonClicked = onPersonClicked,
                    testPainter = testPainter,
                )
            }
        },
    )
}
