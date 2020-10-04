package io.github.mrtry.todolist.di.component

import dagger.Subcomponent
import io.github.mrtry.todolist.app.todo.ui.LoginActivity
import io.github.mrtry.todolist.app.todo.ui.result.AuthResultHandler
import io.github.mrtry.todolist.di.module.ActivityModule
import io.github.mrtry.todolist.di.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface LoginComponent : Component {

    fun inject(activity: LoginActivity)

    val authResultHandler: AuthResultHandler
}