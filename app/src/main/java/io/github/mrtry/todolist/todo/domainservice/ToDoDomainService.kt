package io.github.mrtry.todolist.todo.domainservice

import com.google.firebase.firestore.FirebaseFirestoreException
import io.github.mrtry.todolist.todo.client.ToDoRepository
import io.github.mrtry.todolist.todo.entity.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToDoDomainService
@Inject constructor(
    private val repository: ToDoRepository
) {
    @Throws(FirebaseFirestoreException::class, IllegalArgumentException::class, IllegalStateException::class)
    suspend fun saveToRepository(entity: ToDo) = withContext(Dispatchers.IO) {
        repository.save(entity)
    }

    @Throws(FirebaseFirestoreException::class, IllegalArgumentException::class, IllegalStateException::class)
    suspend fun connectToRepository() = withContext(Dispatchers.IO) {
        repository.connect()
    }
}