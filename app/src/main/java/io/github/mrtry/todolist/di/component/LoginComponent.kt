package io.github.mrtry.todolist.di.component

import dagger.Subcomponent
import io.github.mrtry.todolist.app.splash.ui.SplashActivity
import io.github.mrtry.todolist.app.splash.ui.result.AuthResultHandler
import io.github.mrtry.todolist.di.module.ActivityModule
import io.github.mrtry.todolist.di.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface LoginComponent : Component {

    fun inject(activity: SplashActivity)

    val authResultHandler: AuthResultHandler
}