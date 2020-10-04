package io.github.mrtry.todolist.misc.ui.result

interface ActivityResult<T : ResultHandler, C> {
    val requestCode: Int
    fun getResultHandler(component: C): T
}