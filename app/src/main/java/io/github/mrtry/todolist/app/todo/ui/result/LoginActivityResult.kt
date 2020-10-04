package io.github.mrtry.todolist.app.todo.ui.result

import io.github.mrtry.todolist.di.component.LoginComponent
import io.github.mrtry.todolist.misc.ui.result.ActivityResult

enum class LoginActivityResult(
    override val requestCode: Int
) : ActivityResult<LoginResultHandler, LoginComponent> {
    AUTH(0) {
        override fun getResultHandler(component: LoginComponent): LoginResultHandler =
            component.authResultHandler
    };

    companion object {
        fun valueOf(requestCode: Int): LoginActivityResult =
            values().first { it.requestCode == requestCode }
    }
}