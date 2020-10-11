package io.github.mrtry.todolist.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import dagger.Module
import dagger.Provides
import io.github.mrtry.todolist.di.qualifier.Qualifier
import io.github.mrtry.todolist.di.scope.FragmentScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    fun provideFragment(): Fragment = fragment

    @Provides
    fun provideFragmentManager(): FragmentManager = fragment.requireFragmentManager()

    @Named(Qualifier.FRAGMENT)
    @Provides
    @FragmentScope
    fun provideLifecycleOwner(): LifecycleOwner = fragment

    @Named(Qualifier.FRAGMENT)
    @Provides
    @FragmentScope
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.Main)
}