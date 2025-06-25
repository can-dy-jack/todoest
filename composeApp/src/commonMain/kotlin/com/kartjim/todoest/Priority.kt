package com.kartjim.todoest

enum class Priority(val type: Int) {
    NOT_EMERGENCY_NOT_IMPORTANT(1),
    EMERGENCY_NOT_IMPORTANT(2),
    NOT_EMERGENCY_IMPORTANT(3),
    EMERGENCY_IMPORTANT(4)
}
