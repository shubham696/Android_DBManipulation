package com.example.sampleroomdbapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleObserver
import androidx.room.Room
import com.example.sampleroomdbapp.util.Constants

class MainApplication : Application(), LifecycleObserver {

    private var appDatabase: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()

    }

    private fun createInstanceOfDB(context: Context): AppDatabase {
            return Room.databaseBuilder<AppDatabase>(context,AppDatabase::class.java, Constants.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }

    fun getDBInstance(context: Context): AppDatabase{
        if(null == appDatabase) {
            synchronized(AppDatabase::class.java) {
                if (null == appDatabase) {
                    appDatabase = createInstanceOfDB(context)
                }
            }
        }

        return appDatabase!!
    }

}