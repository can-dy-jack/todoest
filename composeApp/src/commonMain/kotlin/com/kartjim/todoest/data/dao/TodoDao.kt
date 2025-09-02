package com.kartjim.todoest.data.dao

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kartjim.todoest.data.entity.Todo
import kotlinx.coroutines.flow.Flow

data class DateCountResult(
    @ColumnInfo(name = "日期") // 如果列名与变量名不同，可用此注解指定
    val date: String, // 类型应根据实际情况调整，例如 Long (时间戳) 或 String
    @ColumnInfo(name = "数据条数")
    val count: Int
)

@Dao
interface TodoDao {
    @Insert
    suspend fun addTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Update()
    suspend fun updateTodo(todo: Todo)

    @Query("Select * from Todo where id = :id")
    suspend fun getTodo(id: String): Todo

    @Query("select * from Todo")
    fun getTodos(): Flow<List<Todo>>

    @Query("select * from Todo where priority = :priority")
    fun getTodosByPriority(priority: Int): Flow<List<Todo>>

    @Query("Select * from Todo where startTime <= :date and endTime >= :date")
    fun getTodosByDate(date: Long): Flow<List<Todo>>

    // TODO
    @Query("WITH RECURSIVE date_series(day_date) AS (\n" +
            "  SELECT date(:month, 'start of month')\n" +
            "  UNION ALL\n" +
            "  SELECT date(day_date, '+1 day')\n" +
            "  FROM date_series\n" +
            "  WHERE day_date < date(:month, 'start of month', '+1 month', '-1 day')\n" +
            ")\n" +
            "SELECT\n" +
            "  ds.day_date AS 日期,\n" +
            "  COUNT(t.id) AS 数据条数\n" +
            "FROM date_series ds\n" +
            "LEFT JOIN todo t ON\n" +
            "  datetime(t.startTime * 86400, 'unixepoch') < datetime(ds.day_date, '+1 day')\n" +
            "  AND datetime(t.endTime * 86400, 'unixepoch') >= ds.day_date\n" +
            "GROUP BY ds.day_date\n" +
            "ORDER BY ds.day_date;")
    fun getTodoSize(month: String): Flow<List<DateCountResult>>
}
