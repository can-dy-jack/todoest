package com.kartjim.todoest.data.api

import com.kartjim.todoest.data.AppDatabase
import com.kartjim.todoest.data.Priority
import com.kartjim.todoest.data.entity.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

object TodoAPI {
    private val todoDao = AppDatabase.getDatabase().todoDao()

    @OptIn(ExperimentalTime::class)
    suspend fun addTodo(
        title: String,
        description: String,
        startTime: Long,
        endTime: Long,
        isExpirationWarn: Boolean,
        priority: Priority,
        groupId: Int,
        areaId: Int
    ) {
        val todo =
            Todo(
                title = title,
                description = description,
                completed = false,
                startTime = startTime,
                endTime = endTime,
                createdAt = Clock.System.now().epochSeconds,
                updatedAt = 0,
                isExpirationWarn = isExpirationWarn,
                priority = priority,
                groupId = groupId,
                areaId = areaId
            )
        todoDao.addTodo(todo)
    }

    fun getTodos(): Flow<List<Todo>> = todoDao.getTodos()

    fun getTodosByDate(date: Long): Flow<List<Todo>> = todoDao.getTodosByDate(date)

    suspend fun deleteItem(todo: Todo) {
        todoDao.deleteTodo(todo)
    }

    suspend fun updateTodo(
        todo: Todo
//        title: String,
//        description: String,
//        completed: Boolean,
//        startTime: Long,
//        endTime: Long,
//        updatedAt: Long,
//        isExpirationWarn: Boolean,
//        priority: Priority,
//        groupId: Int,
//        areaId: Int
    ) {
        todoDao.updateTodo(todo)
    }

    fun getTodoSize(startTime: Long, endTime: Long): Flow<Int> {
        return todoDao.getTodoSize(startTime, endTime)
    }
}
