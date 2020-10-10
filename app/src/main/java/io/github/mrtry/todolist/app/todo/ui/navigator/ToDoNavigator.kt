package io.github.mrtry.todolist.app.todo.ui.navigator

import android.app.Activity
import io.github.mrtry.todolist.di.scope.ActivityScope
import io.github.mrtry.todolist.misc.ui.navigator.AbsNavigator
import javax.inject.Inject

@ActivityScope
class ToDoNavigator
@Inject constructor(
    private val activity: Activity
) : AbsNavigator(activity)