package io.github.mrtry.todolist.di.component

import dagger.Subcomponent
import io.github.mrtry.todolist.di.module.ActivityModule
import io.github.mrtry.todolist.di.scope.ActivityScope
import io.github.mrtry.todolist.todo.ui.LoginActivity

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface LoginComponent : Component {

    /* activity */
    fun inject(activity: LoginActivity)
}