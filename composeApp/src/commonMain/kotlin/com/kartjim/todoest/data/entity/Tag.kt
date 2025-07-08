package com.kartjim.todoest.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val color: String,
)
