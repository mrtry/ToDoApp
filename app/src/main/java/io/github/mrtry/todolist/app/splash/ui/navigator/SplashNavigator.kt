package io.github.mrtry.todolist.app.splash.ui.navigator

import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import io.github.mrtry.todolist.app.splash.ui.result.SplashActivityResult
import io.github.mrtry.todolist.app.todo.ui.ToDoActivity
import io.github.mrtry.todolist.di.scope.ActivityScope
import io.github.mrtry.todolist.misc.ui.navigator.AbsNavigator
import javax.inject.Inject

@ActivityScope
class SplashNavigator
@Inject constructor(
    private val activity: AppCompatActivity
) : AbsNavigator(activity) {
    fun navigateToToDo() {
        val intent = ToDoActivity.createIntent(activity)
        activity.startActivity(intent)
        finishCurrentActivity()
    }

    fun navigateToAuthentication() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()

        activity.startActivityForResult(intent, SplashActivityResult.AUTH.requestCode)
    }
}