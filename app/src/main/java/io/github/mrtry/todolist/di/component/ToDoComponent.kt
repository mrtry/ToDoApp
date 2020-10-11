package io.github.mrtry.todolist.di.component

import dagger.Subcomponent
import io.github.mrtry.todolist.app.todo.ui.ToDoActivity
import io.github.mrtry.todolist.app.todo.ui.menu.ToDoMenuLogoutActionHandler
import io.github.mrtry.todolist.di.module.ActivityModule
import io.github.mrtry.todolist.di.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ToDoComponent : Component {

    fun inject(activity: ToDoActivity)

    val toDoMenuLogoutActionHandler: ToDoMenuLogoutActionHandler
}