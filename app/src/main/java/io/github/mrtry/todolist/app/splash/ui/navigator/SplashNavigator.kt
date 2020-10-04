package io.github.mrtry.todolist.app.splash.ui.navigator

import android.app.Activity
import com.firebase.ui.auth.AuthUI
import io.github.mrtry.todolist.app.splash.ui.result.SplashActivityResult
import io.github.mrtry.todolist.di.scope.ActivityScope
import io.github.mrtry.todolist.misc.ui.navigator.AbsNavigator
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class SplashNavigator
@Inject constructor(
    private val activity: Activity
) : AbsNavigator(activity) {
    fun navigateToToDo() {
        // TODO
        Timber.d("navigateToToDo() called")
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