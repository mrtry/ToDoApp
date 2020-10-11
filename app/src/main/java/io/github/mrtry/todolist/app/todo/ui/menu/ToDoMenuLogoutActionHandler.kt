package io.github.mrtry.todolist.app.todo.ui.menu

import io.github.mrtry.todolist.di.scope.ActivityScope
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class ToDoMenuLogoutActionHandler
@Inject constructor() : ToDoMenuActionHandler {
    override fun handleAction(): Boolean {
        Timber.d("todo")
        return true
    }
}
