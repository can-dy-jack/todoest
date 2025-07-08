package com.kartjim.todoest.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GroupArea(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val groupId: Int,
    val tagId: Int,
)
