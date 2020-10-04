package io.github.mrtry.todolists.di.module

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import dagger.Module
import dagger.Provides
import io.github.mrtry.todolists.di.scope.ActivityScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
open class ActivityModule(private val activity: AppCompatActivity) {
    @Provides
    fun context(): Context = activity

    @Provides
    fun activity(): Activity = activity

    @Provides
    fun appCompatActivity(): AppCompatActivity = activity

    @Provides
    fun lifecycleOwner(): LifecycleOwner = activity

    @Provides
    @ActivityScope
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.Main)
}