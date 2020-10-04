package io.github.mrtry.todolist.app.splash.viewmodel

import io.github.mrtry.todolist.app.splash.ui.navigator.SplashNavigator
import io.github.mrtry.todolist.auth.AuthenticationClient
import io.github.mrtry.todolist.di.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
class SplashViewModel
@Inject constructor(
    private val authenticationClient: AuthenticationClient,
    private val navigator: SplashNavigator
) {
    fun ensuredLoggedIn() {
        when (authenticationClient.isLoggedIn()) {
            true -> navigator.navigateToToDo()
            false -> navigator.navigateToAuthentication()
        }
    }
}