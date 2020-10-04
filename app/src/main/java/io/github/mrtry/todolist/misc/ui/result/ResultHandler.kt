package io.github.mrtry.todolist.misc.ui.result

import android.content.Intent

interface ResultHandler {
    fun handleResult(resultCode: Int, data: Intent?)
}