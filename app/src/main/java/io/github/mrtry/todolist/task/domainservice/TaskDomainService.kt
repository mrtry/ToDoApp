package io.github.mrtry.todolist.task.domainservice

import com.google.firebase.firestore.FirebaseFirestoreException
import io.github.mrtry.todolist.task.entity.Task
import io.github.mrtry.todolist.task.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskDomainService
@Inject constructor(
    private val repository: ToDoRepository
) {
    @Throws(FirebaseFirestoreException::class, IllegalArgumentException::class, IllegalStateException::class)
    suspend fun saveToRepository(entity: Task) = withContext(Dispatchers.IO) {
        repository.save(entity)
    }

    @Throws(FirebaseFirestoreException::class, IllegalArgumentException::class, IllegalStateException::class)
    suspend fun connectToRepository() = withContext(Dispatchers.IO) {
        repository.connect()
    }
}