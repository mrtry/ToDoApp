package io.github.mrtry.todolist.app.todo.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import io.github.mrtry.todolist.di.scope.ActivityScope
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class TaskViewModel
@Inject constructor() {
    val taskName: MutableLiveData<String> = MutableLiveData("")

    fun onAddTaskButtonClick(v: View?) {
        Timber.d("onClicked")
    }
}