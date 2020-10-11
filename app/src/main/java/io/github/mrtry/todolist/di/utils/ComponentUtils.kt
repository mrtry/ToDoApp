package io.github.mrtry.todolist.di.utils

import android.app.Activity
import io.github.mrtry.todolist.di.Injectable

object ComponentUtils {

    @Suppress("UNCHECKED_CAST")
    fun <T> getComponent(activity: Activity): T {
        require(activity is Injectable<*>) { "context must implement Injectable, but was " + activity.javaClass.simpleName }
        return (activity as Injectable<*>).component as T
    }
}