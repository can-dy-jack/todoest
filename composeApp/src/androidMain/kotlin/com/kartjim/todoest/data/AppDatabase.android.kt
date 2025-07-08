package com.kartjim.todoest.data

import androidx.room.Room
import androidx.room.RoomDatabase
import com.kartjim.todoest.App

//@Suppress(names = ["NO_ACTUAL_FOR_EXPECT"])
//actual object AppDatabaseConstructor :
//    RoomDatabaseConstructor<AppDatabase> {
//    actual override fun initialize(): AppDatabase =
//        getDatabaseBuilder(App.context)
//            .build()
//}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val appContext = App.context
    val dbFile = appContext.getDatabasePath("todoest.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath,
    )
}
