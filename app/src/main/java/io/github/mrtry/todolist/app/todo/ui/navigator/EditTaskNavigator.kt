package io.github.mrtry.todolist.app.todo.ui.navigator

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.github.mrtry.todolist.di.scope.FragmentScope
import io.github.mrtry.todolist.misc.ui.navigator.AbsFragmentNavigator
import javax.inject.Inject

@FragmentScope
class EditTaskNavigator
@Inject constructor(
    activity: AppCompatActivity,
    fragment: Fragment
) : AbsFragmentNavigator(activity, fragment) {
}