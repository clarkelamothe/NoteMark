package com.clarkelamothe.notemark.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.clarkelamothe.notemark.feature_auth.presentation.AuthViewModel
import com.clarkelamothe.notemark.feature_auth.presentation.login.LoginScreenRoot
import com.clarkelamothe.notemark.feature_auth.presentation.onboarding.OnboardingScreenRoot
import com.clarkelamothe.notemark.feature_auth.presentation.register.RegisterScreenRoot
import com.clarkelamothe.notemark.feature_note.presentation.note.NoteScreenRoot
import com.clarkelamothe.notemark.feature_note.presentation.notes.NotesScreenRoot
import org.koin.androidx.compose.navigation.koinNavViewModel

@Composable
fun NavigationRoot(
    navController: NavHostController,
    isLoggedIn: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Route.Home else Route.Auth
    ) {
        authGraph(navController)
        noteGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<Route.Auth>(
        startDestination = Route.Onboarding
    ) {
        composable<Route.Onboarding> {
            OnboardingScreenRoot(
                onGoToLogin = {
                    navController.navigate(Route.Login) {
                        popUpTo<Route.Onboarding> {
                            inclusive = true
                        }
                    }
                },
                onGetStartedClick = {
                    navController.navigate(Route.Register) {
                        popUpTo<Route.Onboarding> {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<Route.Login> {
            LoginScreenRoot(
                onGoToRegister = {
                    navController.navigate(Route.Register) {
                        popUpTo<Route.Login> {
                            inclusive = true
                        }
                    }
                },
                onGoToHome = {
                    navController.navigate(Route.Notes) {
                        popUpTo<Route.Login> {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<Route.Register> {
            RegisterScreenRoot(
                onGoToLogin = {
                    navController.navigate(Route.Login) {
                        popUpTo<Route.Register> {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

private fun NavGraphBuilder.noteGraph(navController: NavHostController) {
    navigation<Route.Home>(
        startDestination = Route.Notes
    ) {
        composable<Route.Notes> {
            NotesScreenRoot(
                onGoToNote = {
                    navController.navigate(Route.Note)
                }
            )
        }

        composable<Route.Note> {
            NoteScreenRoot()
        }
    }
}
