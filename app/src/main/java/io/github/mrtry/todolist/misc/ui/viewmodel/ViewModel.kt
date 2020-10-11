package io.github.mrtry.todolist.misc.ui.viewmodel

import android.os.Bundle

interface ViewModel {
    fun onSaveInstanceState(outState: Bundle) {}
    fun onRestoreInstanceState(savedInstanceState: Bundle) {}
}