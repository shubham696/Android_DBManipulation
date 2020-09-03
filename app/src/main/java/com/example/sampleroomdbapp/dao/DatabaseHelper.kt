package com.example.sampleroomdbapp.dao

import androidx.room.RoomWarnings
import com.example.sampleroomdbapp.AppDatabase
import com.example.sampleroomdbapp.Model.SampleNote

class DatabaseHelper(private val appDatabase: AppDatabase): DatabaseInterface{
    override suspend fun getNoteList(): ArrayList<SampleNote>? {
        return appDatabase.getNoteDao().getAll()
    }

    override suspend fun insertAll(sampleNote: SampleNote) {
        appDatabase.getNoteDao().insert(sampleNote)
    }

}