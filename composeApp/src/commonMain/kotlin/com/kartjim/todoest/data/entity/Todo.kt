package com.kartjim.todoest.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kartjim.todoest.data.Priority

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    var description: String,
    var completed: Boolean,
    var startTime: Long,
    var endTime: Long,
    val createdAt: Long,
    var updatedAt: Long,
    var isExpirationWarn: Boolean,

    var priority: Int = Priority.NOT_EMERGENCY_NOT_IMPORTANT.ordinal,

    var groupId: Int,
    var areaId: Int,
)
