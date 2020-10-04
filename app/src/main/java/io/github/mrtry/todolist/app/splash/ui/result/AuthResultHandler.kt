package io.github.mrtry.todolist.app.splash.ui.result

import android.app.Activity
import android.content.Intent
import io.github.mrtry.todolist.app.splash.ui.navigator.SplashNavigator
import io.github.mrtry.todolist.di.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
class AuthResultHandler
@Inject constructor(
    private val navigator: SplashNavigator
) : SplashResultHandler {
    override fun handleResult(resultCode: Int, data: Intent?) {
        // Firebase-UIの戻り値
        // see: https://github.com/firebase/FirebaseUI-Android/blob/master/auth/README.md#response-codes
        when (resultCode == Activity.RESULT_OK) {
            true -> navigator.navigateToToDo()
            false -> navigator.finishApplication()
        }
    }
}