package io.github.mrtry.todolist.app.todo.ui.menu

import io.github.mrtry.todolist.app.todo.ui.navigator.ToDoNavigator
import io.github.mrtry.todolist.auth.repository.AccountRepository
import io.github.mrtry.todolist.di.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
class ToDoMenuLogoutActionHandler
@Inject constructor(
    private val repository: AccountRepository,
    private val navigator: ToDoNavigator
) : ToDoMenuActionHandler {
    override fun handleAction(): Boolean {
        navigator.showLogoutAlert() {
            repository.logout()
            navigator.navigateToSplash()
        }
        return true
    }
}
