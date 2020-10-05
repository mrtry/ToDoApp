package io.github.mrtry.todolist.app.todo.viewmodel

import io.github.mrtry.todolist.di.scope.ActivityScope
import io.github.mrtry.todolist.misc.ui.viewmodel.ToolbarViewModel
import javax.inject.Inject

@ActivityScope
class ToDoViewModel
@Inject constructor(
    val toolbarViewModel: ToolbarViewModel,
    val taskViewModel: TaskViewModel
) {
}