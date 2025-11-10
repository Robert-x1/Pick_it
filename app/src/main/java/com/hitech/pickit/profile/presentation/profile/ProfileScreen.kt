package com.hitech.pickit.profile.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hitech.pickit.R
import com.hitech.pickit.profile.presentation.profile.components.LanguageProfileMenuItem
import com.hitech.pickit.profile.presentation.profile.components.ProfileHeader
import com.hitech.pickit.profile.presentation.profile.components.ProfileScreenGradient
import com.hitech.pickit.profile.presentation.profile.components.ProfileTopAppBar
import com.hitech.pickit.profile.presentation.profile.components.ThemeMenuItem
import com.hitech.pickit.profile.presentation.profile.components.ToggleMenuItem
import com.hitech.pickit.profile.util.AppTheme
import com.hitech.pickit.ui.theme.PickItTheme

@Composable
fun NewProfileScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        ProfileScreenGradient()

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                ProfileTopAppBar()
            },
            content = {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfileHeader(
                        avatarResId = R.drawable.joker,
                        name = "Sherif",
                        email = "Sherif@gmail.com",
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    ToggleMenuItem(
                        icon = R.drawable.notifications_icon,
                        text = "Notification",
                        isChecked = true,
                        onCheckedChange = {}
                    )

                    LanguageProfileMenuItem(
                        icon = R.drawable.language_icon,
                        text = "Language",
                        selectedLanguage = "English (US)"
                    )

                    ThemeMenuItem(
                        currentTheme = AppTheme.DARK,
                        onThemeChange = {},
                    )

                    HorizontalDivider(
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 5.dp),
                    )

                    LanguageProfileMenuItem(
                        icon = R.drawable.favorite_icon,
                        text = "Favourites"
                    )
                }
            }
        )
    }
}

@Preview
@Composable
private fun NewProfileScreenPreview() {
    PickItTheme {
        NewProfileScreen()
    }
}