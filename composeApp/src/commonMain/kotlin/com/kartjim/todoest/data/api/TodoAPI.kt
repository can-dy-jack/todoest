package com.kartjim.todoest.data.api

import com.kartjim.todoest.data.AppDatabase
import com.kartjim.todoest.data.Priority
import com.kartjim.todoest.data.dao.DateCountResult
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
                priority = priority.type,
                groupId = groupId,
                areaId = areaId
            )
        todoDao.addTodo(todo)
    }

    fun getTodos(): Flow<List<Todo>> = todoDao.getTodos()

    fun getTodosByPriority(priority: Priority): Flow<List<Todo>> = todoDao.getTodosByPriority(priority.type)

    fun getTodosByDate(date: Long): Flow<List<Todo>> = todoDao.getTodosByDate(date)

    suspend fun deleteItem(todo: Todo) {
        todoDao.deleteTodo(todo)
    }

    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }

    fun getTodoSize(month: String): Flow<List<DateCountResult>> {
        return todoDao.getTodoSize(month)
    }
}
