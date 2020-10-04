package io.github.mrtry.todolist.di

import io.github.mrtry.todolist.di.component.Component

interface Injectable<T : Component> {
    val component: T
}