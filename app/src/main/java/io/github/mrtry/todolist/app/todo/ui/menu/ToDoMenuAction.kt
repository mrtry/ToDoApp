package io.github.mrtry.todolist.app.todo.ui.menu

import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.di.component.ToDoComponent
import io.github.mrtry.todolist.misc.ui.menu.MenuAction

enum class ToDoMenuAction(override val menuId: Int) : MenuAction<ToDoMenuActionHandler, ToDoComponent> {
    LOGOUT(R.id.menu_logout) {
        override fun getActionHandler(component: ToDoComponent): ToDoMenuActionHandler =
            component.toDoMenuLogoutActionHandler
    };

    companion object {
        fun valueOf(menuId: Int) =
            values().first { it.menuId == menuId }
    }
}