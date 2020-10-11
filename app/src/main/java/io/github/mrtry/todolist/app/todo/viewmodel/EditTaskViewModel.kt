package io.github.mrtry.todolist.app.todo.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.app.todo.ui.EditTaskDialogFragmentValueHolder
import io.github.mrtry.todolist.app.todo.ui.navigator.EditTaskNavigator
import io.github.mrtry.todolist.di.scope.FragmentScope
import io.github.mrtry.todolist.misc.extension.requireValue
import io.github.mrtry.todolist.misc.ui.viewmodel.ViewModel
import io.github.mrtry.todolist.task.domainservice.TaskDomainService
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private const val KEY_TITLE = "KEY_TITLE"
private const val KEY_DESCRIPTION = "KEY_DESCRIPTION"

@FragmentScope
class EditTaskViewModel
@Inject constructor(
    private val domainService: TaskDomainService,
    private val navigator: EditTaskNavigator,
    private val valueHolder: EditTaskDialogFragmentValueHolder,
    private val coroutineScope: CoroutineScope
) : ViewModel {
    val title: MutableLiveData<String> = MutableLiveData(valueHolder.task.title)
    val description: MutableLiveData<String> = MutableLiveData(valueHolder.task.description)

    val isSaving: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun onSaveInstanceState(outState: Bundle) {
        with(outState) {
            putString(KEY_TITLE, title.requireValue())
            putString(KEY_DESCRIPTION, description.requireValue())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        with(savedInstanceState) {
            getString(KEY_TITLE).also {
                title.value = it
            }
            getString(KEY_DESCRIPTION).also {
                description.value = it
            }
        }
    }

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