package io.github.mrtry.todolist.app.todo.viewmodel

import androidx.lifecycle.MutableLiveData
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.app.todo.ui.EditTaskDialogFragmentValueHolder
import io.github.mrtry.todolist.app.todo.ui.navigator.EditTaskNavigator
import io.github.mrtry.todolist.di.scope.FragmentScope
import io.github.mrtry.todolist.misc.extension.requireValue
import io.github.mrtry.todolist.task.domainservice.TaskDomainService
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@FragmentScope
class EditTaskViewModel
@Inject constructor(
    private val domainService: TaskDomainService,
    private val navigator: EditTaskNavigator,
    private val valueHolder: EditTaskDialogFragmentValueHolder,
    private val coroutineScope: CoroutineScope
) {
    val title: MutableLiveData<String> = MutableLiveData(valueHolder.task.title)
    val description: MutableLiveData<String> = MutableLiveData(valueHolder.task.description)

    val isSaving: MutableLiveData<Boolean> = MutableLiveData(false)

    fun onSaveClick() {
        val task = valueHolder.task.copy(
            title = title.requireValue(),
            description = description.requireValue()
        )

        coroutineScope.launch {
            try {
                isSaving.value = true
                domainService.saveToRepository(task)
                navigator.dismissDialog()
            } catch (e: Exception) {
                if (e is CancellationException) return@launch

                Timber.e(e)
                navigator.showSnackBar(R.string.edit_task_fragment_error_update_task_failed)
            } finally {
                isSaving.value = false
            }
        }
    }
}