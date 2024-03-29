package io.github.mrtry.todolist.app.splash.viewmodel

import io.github.mrtry.todolist.app.splash.ui.navigator.SplashNavigator
import io.github.mrtry.todolist.auth.repository.AccountRepository
import io.github.mrtry.todolist.di.scope.ActivityScope
import io.github.mrtry.todolist.misc.ui.viewmodel.ViewModel
import javax.inject.Inject

@ActivityScope
class SplashViewModel
@Inject constructor(
    private val authenticationClient: AccountRepository,
    private val navigator: SplashNavigator
) : ViewModel {
    fun ensuredLoggedIn() {
        when (authenticationClient.isLoggedIn()) {
            true -> navigator.navigateToToDo()
            false -> navigator.navigateToAuthentication()
        }
    }
}