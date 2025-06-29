package com.kartjim.todoest

data class Todos (
    val id: Int,
    val title: String,
    val description: String,
    val createDate: String,
    val updateDate: String = "",
    val priority: Priority = Priority.NOT_EMERGENCY_NOT_IMPORTANT,
    val groupId: Int = -1,
    val areaId: Int = -1,
    val expired: Boolean = false,
)

