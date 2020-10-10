package io.github.mrtry.todolist.todo.domainservice

import io.github.mrtry.todolist.todo.client.ToDoClient
import io.github.mrtry.todolist.todo.entity.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToDoDomainService
@Inject constructor(
    private val client: ToDoClient
) {
    suspend fun save(entity: ToDo) = withContext(Dispatchers.IO) {
        client.save(entity)
    }

    suspend fun get() = withContext(Dispatchers.IO) {
        client.get()
    }
}