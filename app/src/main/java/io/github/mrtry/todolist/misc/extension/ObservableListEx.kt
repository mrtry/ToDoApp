package io.github.mrtry.todolist.misc.extension

import androidx.databinding.ObservableList

fun <T> ObservableList<T>.addOnListChangedSimpleCallback(func: (items: ObservableList<T>) -> Unit) {
    addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<T>>() {
        override fun onItemRangeChanged(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            func(this@addOnListChangedSimpleCallback)
        }

        override fun onItemRangeRemoved(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            func(this@addOnListChangedSimpleCallback)
        }

        override fun onItemRangeMoved(sender: ObservableList<T>?, fromPosition: Int, toPosition: Int, itemCount: Int) {
            func(this@addOnListChangedSimpleCallback)
        }

        override fun onChanged(sender: ObservableList<T>?) {
            func(this@addOnListChangedSimpleCallback)
        }

        override fun onItemRangeInserted(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            func(this@addOnListChangedSimpleCallback)
        }
    })
}