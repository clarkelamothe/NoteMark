package com.clarkelamothe.notemark.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.clarkelamothe.notemark.feature_auth.presentation.login.LoginScreenRoot
import com.clarkelamothe.notemark.feature_auth.presentation.onboarding.OnboardingScreenRoot
import com.clarkelamothe.notemark.feature_auth.presentation.register.RegisterScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
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
                    navController.navigate(Route.Register)
                }
            )
        }

        composable<Route.Login> {
            LoginScreenRoot(
                onGoToRegister = {
                    navController.navigate(Route.Register)
                }
            )
        }

        composable<Route.Register> {
            RegisterScreenRoot(
                onGoToLogin = {
                    navController.navigate(Route.Login)
                }
            )
        }
    }
}
