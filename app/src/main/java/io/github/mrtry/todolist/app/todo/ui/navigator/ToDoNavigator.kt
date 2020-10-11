package io.github.mrtry.todolist.app.todo.ui.navigator

import android.app.Activity
import io.github.mrtry.todolist.R
import io.github.mrtry.todolist.app.splash.ui.SplashActivity
import io.github.mrtry.todolist.di.scope.ActivityScope
import io.github.mrtry.todolist.misc.ui.navigator.AbsNavigator
import javax.inject.Inject

@ActivityScope
class ToDoNavigator
@Inject constructor(
    private val activity: Activity
) : AbsNavigator(activity) {
    fun navigateToSplash() {
        val intent = SplashActivity.createIntent(activity)
        activity.startActivity(intent)
        finishCurrentActivity()
    }

    fun showLogoutAlert(func: () -> Unit) {
        showAlert(
            R.string.to_do_activity_alert_logout,
            android.R.string.ok,
            { _, _ -> func() },
            android.R.string.cancel
        )
    }
}