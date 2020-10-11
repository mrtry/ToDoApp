package io.github.mrtry.todolist.task.entity

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Task(
    val id: String? = null,
    var title: String = "",
    var description: String = "",
    var isComplete: Boolean = false,
    val createdAt: Timestamp? = null
) : Parcelable