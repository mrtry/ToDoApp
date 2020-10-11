package io.github.mrtry.todolist.app.todo.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.app.todo.ui.navigator.ToDoNavigator
import io.github.mrtry.todolist.misc.extension.observeNonNull
import io.github.mrtry.todolist.misc.extension.requireValue
import io.github.mrtry.todolist.task.domainservice.TaskDomainService
import io.github.mrtry.todolist.task.entity.Task
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

class ToDoListItemViewModel(
    todo: Task,
    lifecycleOwner: LifecycleOwner,
    private val navigator: ToDoNavigator,
    private val domainService: TaskDomainService,
    private val coroutineScope: CoroutineScope
) {
    val todo: MutableLiveData<Task> = MutableLiveData(todo)
    val isComplete: MutableLiveData<Boolean> = MutableLiveData(todo.isComplete)
    val isSaving: MutableLiveData<Boolean> = MutableLiveData(false)

    private var currentIsCompleteState: Boolean = isComplete.requireValue()

    init {
        isComplete.observeNonNull(lifecycleOwner) {
            this.todo.value = this.todo.requireValue().copy(isComplete = it)
        }

        this.todo.observeNonNull(lifecycleOwner) {
            if (it.isComplete == currentIsCompleteState) return@observeNonNull

            coroutineScope.launch {
                isSaving.value = true

                try {
                    domainService.saveToRepository(it)
                    currentIsCompleteState = isComplete.requireValue()
                } catch (e: Exception) {
                    if (e is CancellationException) return@launch

                    Timber.e(e)
                    navigator.showSnackBar(R.string.to_do_activity_error_update_task_failed)
                    isComplete.value = currentIsCompleteState
                } finally {
                    isSaving.value = false
                }
            }
        }
    }
}