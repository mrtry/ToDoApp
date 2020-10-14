package io.github.mrtry.todolist.task.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.MetadataChanges
import io.github.mrtry.todolist.auth.repository.AccountRepository
import io.github.mrtry.todolist.misc.extension.await
import io.github.mrtry.todolist.misc.extension.getDataFlow
import io.github.mrtry.todolist.task.entity.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

private const val COLLECTION_PATH = "task"

@Singleton
class ToDoRepository
@Inject constructor(
    private val accountRepository: AccountRepository
) {
    private val db = FirebaseFirestore.getInstance()

    suspend fun save(entity: Task) = withContext(Dispatchers.IO) {
        val id = entity.id ?: db.collection(COLLECTION_PATH).document().id
        val ownerId = entity.ownerId ?: accountRepository.getUserId()
        val timestamp = entity.createdAt ?: Timestamp(Date())

        db.collection(COLLECTION_PATH)
            .document(id)
            .set(
                entity.copy(
                    id = id,
                    ownerId = ownerId,
                    createdAt = timestamp
                )
            )
            .await()
    }

    suspend fun remove(entity: Task) = withContext(Dispatchers.IO) {
        val id = entity.id
            ?: throw IllegalStateException("entity.id is empty")

        db.collection(COLLECTION_PATH)
            .document(id)
            .delete()
            .await()
    }

    fun connect(): Flow<List<Task>> =
        db.collection(COLLECTION_PATH)
            .getDataFlow(MetadataChanges.INCLUDE) { querySnapshot ->
                querySnapshot?.documents?.mapNotNull {
                    val entity = it.toObject(Task::class.java)
                    when (entity?.ownerId == accountRepository.getUserId()) {
                        true -> entity
                        else -> null
                    }
                }
                    ?.toMutableList()
                    .also { list -> list?.sortBy { it.createdAt } }
                    ?.toList()
                    ?: listOf()
            }
}