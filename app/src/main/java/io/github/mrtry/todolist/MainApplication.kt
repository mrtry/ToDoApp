package io.github.mrtry.todolist

import android.app.Application
import android.content.Context
import io.github.mrtry.todolist.di.component.AppComponent
import io.github.mrtry.todolist.di.component.DaggerAppComponent
import timber.log.Timber


class MainApplication : Application() {
    companion object {
        fun getComponent(context: Context): AppComponent {
            return (context.applicationContext as MainApplication).appComponent
        }
    }

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build().also {
            it.inject(this)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}