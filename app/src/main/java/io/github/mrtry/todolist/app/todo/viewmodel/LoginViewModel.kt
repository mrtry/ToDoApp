package io.github.mrtry.todolist.app.todo.viewmodel

import io.github.mrtry.todolist.app.todo.ui.navigator.LoginNavigator
import io.github.mrtry.todolist.auth.AuthenticationClient
import io.github.mrtry.todolist.di.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
class LoginViewModel
@Inject constructor(
    private val authenticationClient: AuthenticationClient,
    private val navigator: LoginNavigator
) {
    fun ensuredLoggedIn() {
        if (!authenticationClient.isLoggedIn()) {
            navigator.navigateToAuthentication()
        }
    }
}