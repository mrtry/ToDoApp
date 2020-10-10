package io.github.mrtry.todolist.todo.entity

import com.google.firebase.Timestamp

data class ToDo(
    val id: String? = null,
    var title: String = "",
    var description: String = "",
    var isComplete: Boolean = false,
    val createdAt: Timestamp? = null
)