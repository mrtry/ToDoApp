package io.github.mrtry.todolist.app.todo.ui.navigator

import androidx.appcompat.app.AppCompatActivity
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.app.splash.ui.SplashActivity
import io.github.mrtry.todolist.app.todo.ui.EditTaskDialogFragment
import io.github.mrtry.todolist.di.scope.ActivityScope
import io.github.mrtry.todolist.misc.ui.navigator.AbsNavigator
import io.github.mrtry.todolist.task.entity.Task
import javax.inject.Inject

@ActivityScope
class ToDoNavigator
@Inject constructor(
    private val activity: AppCompatActivity
) : AbsNavigator(activity) {
    fun navigateToSplash() {
        val intent = SplashActivity.createIntent(activity)
        activity.startActivity(intent)
        finishCurrentActivity()
    }

    fun showLogoutAlert(func: () -> Unit) {
        showAlert(
            R.string.to_do_activity_alert_logout,
            R.string.to_do_activity_alert_label_logout,
            { _, _ -> func() },
            android.R.string.cancel
        )
    }

    fun showConfirmDeleteAlert(func: () -> Unit) {
        showAlert(
            R.string.to_do_activity_alert_confirm_delete,
            R.string.to_do_activity_alert_label_delete,
            { _, _ -> func() },
            android.R.string.cancel
        )
    }

    fun showEditTask(task: Task) {
        val fragmentDialog = EditTaskDialogFragment.newInstance(task)
        fragmentDialog.show(activity.supportFragmentManager, EditTaskDialogFragment.TAG)
    }
}