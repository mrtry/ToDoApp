package io.github.mrtry.todolist.misc.ui.binding

import androidx.databinding.ViewDataBinding

interface Bindable<out T : ViewDataBinding> {

    val viewBinding: T
}