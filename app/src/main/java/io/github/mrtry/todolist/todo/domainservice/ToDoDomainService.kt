package io.github.mrtry.todolist.todo.domainservice

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
    suspend fun saveToRepository(entity: ToDo) = withContext(Dispatchers.IO) {
        repository.save(entity)
    }

    suspend fun connectToRepository() = withContext(Dispatchers.IO) {
        repository.connect()
    }
}