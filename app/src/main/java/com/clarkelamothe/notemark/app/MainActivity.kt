package com.clarkelamothe.notemark.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.clarkelamothe.notemark.app.navigation.NavigationRoot
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        splashScreen.setKeepOnScreenCondition {
            true
            lifecycleScope.launch {
                delay(2000L)
            }
            false
        }
        setContent {
            val navController = rememberNavController()
            NoteMarkTheme {
                NavigationRoot(navController)
            }
        }
    }
}
