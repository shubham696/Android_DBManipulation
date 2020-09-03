package com.example.sampleroomdbapp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomWarnings
import com.example.sampleroomdbapp.Model.SampleNote
import com.example.sampleroomdbapp.dao.NoteDao

@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
@Database(entities = [SampleNote::class],version = 3, exportSchema = false)
abstract class AppDatabase(): RoomDatabase() {

    abstract fun getNoteDao(): NoteDao


}