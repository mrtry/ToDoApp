package io.github.mrtry.todolist.app.todo.viewmodel.converter

import io.github.mrtry.todolist.app.todo.viewmodel.ToDoListItemViewModel
import io.github.mrtry.todolist.di.scope.ActivityScope
import io.github.mrtry.todolist.todo.entity.ToDo
import javax.inject.Inject

@ActivityScope
class ToDoListItemViewModelConverter
@Inject constructor() {
    fun convert(entity: ToDo): ToDoListItemViewModel =
        ToDoListItemViewModel(
            entity
        )
}