package io.github.mrtry.todolist.di.component

import dagger.Component
import io.github.mrtry.todolist.MainApplication
import io.github.mrtry.todolist.di.module.ActivityModule
import io.github.mrtry.todolist.di.module.AppModule

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(application: MainApplication)

    fun plusLoginComponent(module: ActivityModule): LoginComponent
}