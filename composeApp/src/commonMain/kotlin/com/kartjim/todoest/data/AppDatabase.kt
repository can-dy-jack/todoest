package com.kartjim.todoest.data

import androidx.room.AutoMigration
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.kartjim.todoest.data.dao.TodoDao
import com.kartjim.todoest.data.entity.Todo
import kotlin.concurrent.Volatile

@Database(
    entities = [Todo::class],
    version = 3,
//    autoMigrations = [AutoMigration (from = 2, to = 3)]
)
//@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(): AppDatabase {
            instance?.let {
                return it
            }
            return getDatabaseBuilder()
                .build().also {
                    instance = it
                }
        }
    }
}

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>

//@Suppress("NO_ACTUAL_FOR_EXPECT")
//expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
//    override fun initialize(): AppDatabase
//}
