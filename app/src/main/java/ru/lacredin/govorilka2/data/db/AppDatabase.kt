package ru.lacredin.govorilka2.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import ru.lacredin.govorilka2.data.db.dao.UserDao
import ru.lacredin.govorilka2.data.db.entity.User

@Database(
    version = 1,
    entities = [
        User::class
    ],
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class AppDatabase : RoomDatabase() {
    companion object{
        const val GOVORILKA_BD_NAME = "govorilka_bd_name"
    }


    abstract fun userDao(): UserDao
}