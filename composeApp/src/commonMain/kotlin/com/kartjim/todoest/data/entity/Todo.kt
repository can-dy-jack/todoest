package com.kartjim.todoest.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kartjim.todoest.data.Priority

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val completed: Boolean,
    val startTime: Long,
    val endTime: Long,
    val createdAt: Long,
    val updatedAt: Long,
    val isExpirationWarn: Boolean,
    val priority: Priority = Priority.NOT_EMERGENCY_NOT_IMPORTANT,

    val groupId: Int,
    val areaId: Int,
)
