package com.clarkelamothe.notemark.app.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.clarkelamothe.notemark.app.navigation.NavigationRoot
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        splashScreen.setKeepOnScreenCondition {
            viewModel.state.isCheckingAuth
        }
        setContent {
            if (!viewModel.state.isCheckingAuth) {
                val navController = rememberNavController()
                NoteMarkTheme {
                    NavigationRoot(
                        navController = navController,
                        isLoggedIn = viewModel.state.isLoggedIn
                    )
                }
            }
        }
    }
}
