package io.github.mrtry.todolists.di.component

import dagger.Component
import io.github.mrtry.todolists.MainApplication
import io.github.mrtry.todolists.di.module.ActivityModule
import io.github.mrtry.todolists.di.module.AppModule

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(application: MainApplication)

    fun plusLoginComponent(module: ActivityModule): LoginComponent
}