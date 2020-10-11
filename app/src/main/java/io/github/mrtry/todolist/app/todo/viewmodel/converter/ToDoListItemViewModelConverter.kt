package io.github.mrtry.todolist.app.todo.viewmodel.converter

import androidx.lifecycle.LifecycleOwner
import io.github.mrtry.todolist.app.todo.ui.navigator.ToDoNavigator
import io.github.mrtry.todolist.app.todo.viewmodel.ToDoListItemViewModel
import io.github.mrtry.todolist.di.scope.ActivityScope
import io.github.mrtry.todolist.task.domainservice.TaskDomainService
import io.github.mrtry.todolist.task.entity.Task
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@ActivityScope
class ToDoListItemViewModelConverter
@Inject constructor(
    private val lifecycleOwner: LifecycleOwner,
    private val navigator: ToDoNavigator,
    private val domainService: TaskDomainService,
    private val coroutineScope: CoroutineScope
) {
    fun convert(entity: Task): ToDoListItemViewModel =
        ToDoListItemViewModel(
            entity,
            lifecycleOwner,
            navigator,
            domainService,
            coroutineScope
        )
}