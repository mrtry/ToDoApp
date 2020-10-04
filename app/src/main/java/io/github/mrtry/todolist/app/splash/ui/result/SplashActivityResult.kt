package io.github.mrtry.todolist.app.splash.ui.result

import io.github.mrtry.todolist.di.component.LoginComponent
import io.github.mrtry.todolist.misc.ui.result.ActivityResult

enum class SplashActivityResult(
    override val requestCode: Int
) : ActivityResult<SplashResultHandler, LoginComponent> {
    AUTH(0) {
        override fun getResultHandler(component: LoginComponent): SplashResultHandler =
            component.authResultHandler
    };

    companion object {
        fun valueOf(requestCode: Int): SplashActivityResult =
            values().first { it.requestCode == requestCode }
    }
}