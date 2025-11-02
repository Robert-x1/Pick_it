package com.hitech.pickit.profile.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hitech.pickit.R
import com.hitech.pickit.profile.presentation.profile.components.ListProfileMenuItem
import com.hitech.pickit.profile.presentation.profile.components.SettingProfileMenuItem
import com.hitech.pickit.profile.presentation.profile.components.ProfileTopAppBar
import com.hitech.pickit.ui.theme.PickItTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ProfileTopAppBar()
        },
        content = {innerPadding ->
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text="Settings",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )

                SettingProfileMenuItem(
                    icon = R.drawable.theme_icon,
                    text = "Theme",
                    menuContent = {onDismiss ->
                        DropdownMenuItem(
                            text = { Text("System default") },
                            onClick = {
                                onDismiss()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Light") },
                            onClick = {
                                onDismiss()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Dark") },
                            onClick = {
                                onDismiss()
                            }
                        )
                    }
                )

                SettingProfileMenuItem(
                    icon = R.drawable.language_icon,
                    text = "Language",
                    menuContent = {onDismiss ->
                        DropdownMenuItem(
                            text = { Text("العربية") },
                            onClick = {
                                onDismiss()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("English") },
                            onClick = {
                                onDismiss()
                            }
                        )
                    }
                )

                Text(
                    text="Lists",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )

                ListProfileMenuItem(
                    icon = R.drawable.favorite_icon,
                    text = "Favourites"
                )
            }

        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProfileScreenPreview() {
    PickItTheme {
            ProfileScreen()
    }
}