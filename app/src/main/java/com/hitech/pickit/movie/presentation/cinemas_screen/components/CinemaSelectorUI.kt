package com.hitech.pickit.movie.presentation.cinemas_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.hitech.pickit.ui.theme.PickItTheme

@Composable
fun CinemaSelectorUI(
    modifier: Modifier = Modifier
) {
    val backgroundColor = Color(0xFF2C2C2C)
    var selectedCinema by remember { mutableStateOf("Select Cinema") }
    var selectedLocation by remember { mutableStateOf("Select Governorate") }

    var expandedCinema by remember { mutableStateOf(false) }
    var expandedLocation by remember { mutableStateOf(false) }

    val cinemas = listOf(
        "VOX Cinemas – Mall of Egypt",
        "VOX Cinemas – City Centre Almaza",
        "VOX Cinemas – City Centre Alexandria",
        "Renaissance Cinema – Dandy Mall",
        "Renaissance Cinema – Down Town",
        "Zamalek Cinema",
        "Galaxy Cinema – Gezira Plaza",
        "Cinepolis – Americana Plaza Zayed",
        "IMAX – Mall of Arabia",
        "Miami Cinema – Alexandria",
        "Odeon Cinema – Cairo",
        "Cinema Karim – Downtown Cairo",
        "Metro Cinema – Cairo",
        "CineMax – Mansoura",
        "Royal Cinema – Tanta"
    )

    val locations = listOf(
        "Cairo",
        "Giza",
        "Alexandria",
        "Qalyubia",
        "Dakahlia",
        "Beheira",
        "Kafr El Sheikh",
        "Gharbia",
        "Monufia",
        "Sharqia",
        "Damietta",
        "Port Said",
        "Ismailia",
        "Suez",
        "North Sinai",
        "South Sinai",
        "Beni Suef",
        "Fayoum",
        "Minya",
        "Assiut",
        "Sohag",
        "Qena",
        "Luxor",
        "Aswan",
        "Red Sea",
        "New Valley",
        "Matrouh"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier.height(75.dp),
            shape = RoundedCornerShape(20),
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor
            ),
            elevation = CardDefaults.cardElevation(10.dp),
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = Color.White,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp)
            )
        }

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {

            DropdownCard(
                label = "Cinema",
                selectedItem = selectedCinema,
                expanded = expandedCinema,
                items = cinemas,
                onExpand = { expandedCinema = true },
                onDismiss = { expandedCinema = false },
                onSelect = {
                    selectedCinema = it
                    expandedCinema = false
                },
                backgroundColor = backgroundColor,
                modifier = Modifier.fillMaxWidth(0.5f)
            )


            DropdownCard(
                label = "Location",
                selectedItem = selectedLocation,
                expanded = expandedLocation,
                items = locations,
                onExpand = { expandedLocation = true },
                onDismiss = { expandedLocation = false },
                onSelect = {
                    selectedLocation = it
                    expandedLocation = false
                },
                backgroundColor = backgroundColor,
                modifier = Modifier.fillMaxWidth(1f)
            )
        }
    }
}

@Composable
private fun DropdownCard(
    label: String,
    selectedItem: String,
    expanded: Boolean,
    items: List<String>,
    onExpand: () -> Unit,
    onDismiss: () -> Unit,
    onSelect: (String) -> Unit,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = MaterialTheme.colorScheme.onTertiary
        ),
        elevation = CardDefaults.cardElevation(8.dp),
        onClick = onExpand
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    label,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Icon(
                    Icons.Outlined.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Text(
                selectedItem,
                color = Color.Gray,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = onDismiss,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(horizontal = 8.dp)
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = { onSelect(item) }
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CinemaSelectorUiPreview() {
    PickItTheme {
        CinemaSelectorUI()
    }

}