package com.kartjim.todoest.data

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import java.io.File

//@Suppress(names = ["NO_ACTUAL_FOR_EXPECT"])
//actual object AppDatabaseConstructor :
//    RoomDatabaseConstructor<AppDatabase> {
//    actual override fun initialize(): AppDatabase =
//        getDatabaseBuilder()
//            .build()
//}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    // 这个地方 demo 是 temp 文件夹，后续换其他地方
    val dbFile = File(System.getProperty("java.io.tmpdir"), "todoest.db")
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile.absolutePath,
    )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
}
