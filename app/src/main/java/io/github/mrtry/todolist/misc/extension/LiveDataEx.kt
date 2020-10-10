package io.github.mrtry.todolist.misc.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (T) -> Unit) {
    this.observe(owner, Observer {
        if (it != null) {
            observer(it)
        }
    })
}

fun <T> LiveData<T>.requireValue(): T {
    value ?: throw IllegalStateException("Value has not been set yet.")
    return value!!
}