package io.github.mrtry.todolist.task.entity

import com.google.firebase.Timestamp

data class Task(
    val id: String? = null,
    var title: String = "",
    var description: String = "",
    var isComplete: Boolean = false,
    val createdAt: Timestamp? = null
)