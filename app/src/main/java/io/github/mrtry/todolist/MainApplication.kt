package io.github.mrtry.todolist

import android.app.Application
import android.content.Context
import io.github.mrtry.todolist.di.component.AppComponent
import io.github.mrtry.todolist.di.component.DaggerAppComponent
import io.github.mrtry.todolist.misc.ui.WholeActivityDelegate
import timber.log.Timber
import javax.inject.Inject


class MainApplication : Application() {
    companion object {
        fun getComponent(context: Context): AppComponent {
            return (context.applicationContext as MainApplication).appComponent
        }
    }

    @Inject
    lateinit var wholeActivityDelegate: WholeActivityDelegate

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build().also {
            it.inject(this)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        appComponent
        registerActivityLifecycleCallbacks(wholeActivityDelegate)
    }
}