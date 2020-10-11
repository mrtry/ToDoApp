package io.github.mrtry.todolist.task.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.MetadataChanges
import io.github.mrtry.todolist.misc.extension.await
import io.github.mrtry.todolist.misc.extension.getDataFlow
import io.github.mrtry.todolist.task.entity.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

private const val COLLECTION_PATH = "ToDo"

@Singleton
class ToDoRepository
@Inject constructor() {
    private val db = FirebaseFirestore.getInstance()

    suspend fun save(entity: Task) = withContext(Dispatchers.IO) {
        val id = entity.id ?: db.collection(COLLECTION_PATH).document().id
        val timestamp = entity.createdAt ?: Timestamp(Date())

        db.collection(COLLECTION_PATH)
            .document(id)
            .set(
                entity.copy(
                    id = id,
                    createdAt = timestamp
                )
            )
            .await()
    }

    fun connect(): Flow<List<Task>> =
        db.collection(COLLECTION_PATH)
            .getDataFlow(MetadataChanges.INCLUDE) { querySnapshot ->
                querySnapshot?.documents?.mapNotNull {
                    it.toObject(Task::class.java)
                }
                    ?.toMutableList()
                    .also { list -> list?.sortBy { it.createdAt } }
                    ?.toList()
                    ?: listOf()
            }
}