package io.github.mrtry.todolist.app.todo.viewmodel

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.app.todo.ui.navigator.ToDoNavigator
import io.github.mrtry.todolist.di.scope.ActivityScope
import io.github.mrtry.todolist.misc.extension.requireValue
import io.github.mrtry.todolist.misc.ui.viewmodel.ViewModel
import io.github.mrtry.todolist.task.domainservice.TaskDomainService
import io.github.mrtry.todolist.task.entity.Task
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class TaskViewModel
@Inject constructor(
    private val navigator: ToDoNavigator,
    private val domainService: TaskDomainService,
    private val coroutineScope: CoroutineScope
) : ViewModel, TextView.OnEditorActionListener {
    val taskName: MutableLiveData<String> = MutableLiveData("")
    val isSaving: MutableLiveData<Boolean> = MutableLiveData(false)

    fun onAddTaskButtonClick(v: View?) {
        coroutineScope.launch {
            if (taskName.requireValue().isEmpty()) return@launch

            isSaving.value = true
            val todo = Task(title = taskName.value.orEmpty())

            try {
                domainService.saveToRepository(todo)
                taskName.value = ""
            } catch (e: Exception) {
                if (e is CancellationException) return@launch

                Timber.e(e)
                navigator.showSnackBar(R.string.to_do_activity_error_add_task_failed)
            } finally {
                isSaving.value = false
            }
        }
    }

    override fun onEditorAction(v: TextView?, actionId: Int, keyEvent: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            onAddTaskButtonClick(v)
        }
        return false
    }
}