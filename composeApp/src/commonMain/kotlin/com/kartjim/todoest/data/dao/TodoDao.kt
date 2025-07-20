package com.kartjim.todoest.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kartjim.todoest.data.entity.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert
    suspend fun addTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Query("Select * from Todo where id = :id")
    suspend fun getTodo(id: String): Todo

    @Query("select * from Todo")
    fun getTodos(): Flow<List<Todo>>

    @Query("Select * from Todo where startTime = :date")
    fun getTodosByDate(date: Long): Flow<List<Todo>>
}
