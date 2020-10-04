package io.github.mrtry.todolists.di

import io.github.mrtry.todolists.di.component.Component

interface Injectable<T : Component> {
    val component: T
}