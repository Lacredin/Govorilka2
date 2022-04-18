package ru.lacredin.govorilka2

import android.app.Application
import androidx.room.Room
import ru.lacredin.govorilka2.data.db.AppDatabase
import ru.lacredin.govorilka2.data.db.AppDatabase.Companion.GOVORILKA_BD_NAME

class App: Application() {
    companion object{
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            GOVORILKA_BD_NAME
        ).build()
    }
}