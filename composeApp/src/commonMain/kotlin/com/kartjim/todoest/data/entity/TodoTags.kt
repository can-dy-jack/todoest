package com.kartjim.todoest.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoTags(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val todoId: Int,
    val tagId: Int,
)
