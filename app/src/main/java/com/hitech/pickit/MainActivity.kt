package com.hitech.pickit

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen // تأكد من وجود المكتبة دي
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hitech.pickit.movie.presentation.profile.util.AppTheme
import com.hitech.pickit.movie.presentation.profile.viewmodel.ProfileViewModel
import com.hitech.pickit.ui.MainViewModel
import com.hitech.pickit.ui.PickItApp
import com.hitech.pickit.ui.theme.PickItTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        splashScreen.setKeepOnScreenCondition {
            mainViewModel.isLoading.value
        }

        setContent {
            val profileViewModel: ProfileViewModel = hiltViewModel()
            val appTheme by profileViewModel.theme.collectAsStateWithLifecycle()
            val isDark = appTheme == AppTheme.DARK

            PickItTheme(darkTheme = isDark) {

                val startDest by mainViewModel.startDestination.collectAsStateWithLifecycle()
                val isLoading by mainViewModel.isLoading.collectAsStateWithLifecycle()
                if (!isLoading) {
                    PickItApp(
                        startDestination = startDest,
                        viewModel = mainViewModel
                    )
                }
            }
        }
    }
}