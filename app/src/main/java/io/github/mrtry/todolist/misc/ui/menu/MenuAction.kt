package io.github.mrtry.todolist.misc.ui.menu

interface MenuAction<T : ActionHandler, C> {
    val menuId: Int
    fun getActionHandler(component: C): T
}