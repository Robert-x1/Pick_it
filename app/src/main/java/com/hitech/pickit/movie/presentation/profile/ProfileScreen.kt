package com.hitech.pickit.movie.presentation.profile
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hitech.pickit.R
import com.hitech.pickit.movie.presentation.profile.components.LanguageDialog
import com.hitech.pickit.movie.presentation.profile.components.LanguageProfileMenuItem
import com.hitech.pickit.movie.presentation.profile.components.ProfileHeader
import com.hitech.pickit.movie.presentation.profile.components.ProfileScreenGradient
import com.hitech.pickit.movie.presentation.profile.components.ProfileTopAppBar
import com.hitech.pickit.movie.presentation.profile.components.ThemeMenuItem
import com.hitech.pickit.movie.presentation.profile.components.ToggleMenuItem
import com.hitech.pickit.movie.presentation.profile.viewmodel.ProfileViewModel
import com.hitech.pickit.ui.theme.PickItTheme
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val currentTheme by viewModel.theme.collectAsStateWithLifecycle()
    val currentLanguage by viewModel.currentLanguage.collectAsStateWithLifecycle()

    var showLanguageDialog by remember { mutableStateOf(false) }

    var isNotificationChecked by remember { mutableStateOf(true) }

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
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
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
                        text = stringResource(R.string.Notifications),
                        isChecked = isNotificationChecked,
                        onCheckedChange = {isNotificationChecked = !isNotificationChecked}
                    )

                    LanguageProfileMenuItem(
                        icon = R.drawable.language_icon,
                        text = stringResource(R.string.Language),
                        selectedLanguage = currentLanguage.displayName,
                        onClick = { showLanguageDialog = true }
                    )

                    ThemeMenuItem(
                        currentTheme = currentTheme,
                        onThemeChange = { viewModel.setTheme(it) } ,
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 5.dp),
                    )

//                    LanguageProfileMenuItem(
//                        icon = R.drawable.favorite_icon,
//                        text = "Favourites"
//                    )
                }
            }
        )

        LanguageDialog(
            openDialog = showLanguageDialog,
            currentAppLanguage = currentLanguage,
            onLanguageSelected = { selectedLanguage ->
                viewModel.setLanguage(selectedLanguage)
                showLanguageDialog = false
            },
            onDismiss = { showLanguageDialog = false }
        )
    }
}

@Preview
@Composable
private fun NewProfileScreenPreview() {
    PickItTheme {
        ProfileScreen()
    }
}