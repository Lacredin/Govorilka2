package ru.lacredin.govorilka2

import android.app.Application
import ru.lacredin.govorilka2.data.db.AppDatabase

class App: Application() {
    companion object{
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
//        db = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java,
//            GOVORILKA_BD_NAME
//        ).build()
    }
}