package io.github.mrtry.todolist.app.todo.viewmodel

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.app.todo.ui.navigator.ToDoNavigator
import io.github.mrtry.todolist.misc.extension.observeNonNull
import io.github.mrtry.todolist.misc.extension.requireValue
import io.github.mrtry.todolist.misc.ui.viewmodel.ViewModel
import io.github.mrtry.todolist.task.domainservice.TaskDomainService
import io.github.mrtry.todolist.task.entity.Task
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

class ToDoListItemViewModel(
    task: Task,
    lifecycleOwner: LifecycleOwner,
    private val navigator: ToDoNavigator,
    private val domainService: TaskDomainService,
    private val coroutineScope: CoroutineScope
) : ViewModel {
    val task: MutableLiveData<Task> = MutableLiveData(task)
    val isComplete: MutableLiveData<Boolean> = MutableLiveData(task.isComplete)
    val isSaving: MutableLiveData<Boolean> = MutableLiveData(false)

    private var currentIsCompleteState: Boolean = isComplete.requireValue()

    init {
        isComplete.observeNonNull(lifecycleOwner) {
            this.task.value = this.task.requireValue().copy(isComplete = it)
        }

        this.task.observeNonNull(lifecycleOwner) {
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

    fun onItemClick(v: View?) {
        navigator.showEditTask(task.requireValue())
    }
}