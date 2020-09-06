package com.example.sampleroomdbapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sampleroomdbapp.model.SampleNote
import com.example.sampleroomdbapp.database.dao.NoteDao

@Database(entities = [SampleNote::class],version = 1, exportSchema = false)
abstract class AppDatabase(): RoomDatabase() {

    abstract fun getNoteDao(): NoteDao


}