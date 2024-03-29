package io.github.mrtry.todolist.app.todo.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.app.todo.ui.navigator.ToDoNavigator
import io.github.mrtry.todolist.app.todo.viewmodel.converter.ToDoListItemViewModelConverter
import io.github.mrtry.todolist.di.scope.ActivityScope
import io.github.mrtry.todolist.misc.ui.viewmodel.ToolbarViewModel
import io.github.mrtry.todolist.misc.ui.viewmodel.ViewModel
import io.github.mrtry.todolist.task.domainservice.TaskDomainService
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class ToDoViewModel
@Inject constructor(
    val toolbarViewModel: ToolbarViewModel,
    val taskViewModel: TaskViewModel,
    private val navigator: ToDoNavigator,
    private val taskDomainService: TaskDomainService,
    private val converter: ToDoListItemViewModelConverter,
    private val coroutineScope: CoroutineScope
) : ViewModel, SwipeRefreshLayout.OnRefreshListener {
    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData(false)
    val showEmptyStatus: MutableLiveData<Boolean> = MutableLiveData(false)

    val items: ObservableList<ToDoListItemViewModel> = ObservableArrayList()

    override fun onRefresh() {
        isRefreshing.value = true
        load()
    }

    fun load() {
        coroutineScope.launch {
            try {
                taskDomainService.connectToRepository().collect { todos ->
                    items.clear()
                    todos.map {
                        items.add(
                            converter.convert(it)
                        )
                    }

                    isRefreshing.value = false
                    showEmptyStatus.value = items.isEmpty()
                }
            } catch (e: Exception) {
                if (e is CancellationException) return@launch

                Timber.e(e)
                isRefreshing.value = false
                showEmptyStatus.value = items.isEmpty()
                navigator.showSnackBar(R.string.to_do_activity_error_get_task_failed)
            }
        }
    }
}