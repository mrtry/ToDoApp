package io.github.mrtry.todolists

import android.app.Application
import android.content.Context
import io.github.mrtry.todolists.di.component.AppComponent
import io.github.mrtry.todolists.di.component.DaggerAppComponent


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
    }
}