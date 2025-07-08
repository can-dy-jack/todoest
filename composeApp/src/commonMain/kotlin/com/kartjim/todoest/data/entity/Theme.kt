package com.kartjim.todoest.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Theme(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val base: Int = 0,
    val primaryColor: String = "#b37feb",
    val secondaryColor: String = "#722ed1",
    val backgroundColor: String = "#fff",
    val textColor: String = "#555",
    val fontSize: Int = 14,
    val fontFamily: String = "",
    val fontWeight: String = "normal",
    val borderRadius: Int = 10,
    val shadow: String = "",
)
