package io.github.mrtry.todolist.di.component

import dagger.Subcomponent
import io.github.mrtry.todolist.app.todo.ui.EditTaskDialogFragment
import io.github.mrtry.todolist.di.module.FragmentModule
import io.github.mrtry.todolist.di.scope.FragmentScope

@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface EditTaskComponent : Component {
    fun inject(fragment: EditTaskDialogFragment)
}