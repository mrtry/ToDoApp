package io.github.mrtry.todolists.di.component

import dagger.Subcomponent
import io.github.mrtry.todolists.di.module.ActivityModule
import io.github.mrtry.todolists.di.scope.ActivityScope
import io.github.mrtry.todolists.todo.ui.LoginActivity

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface LoginComponent : Component {

    /* activity */
    fun inject(activity: LoginActivity)
}