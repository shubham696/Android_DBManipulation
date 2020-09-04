package com.example.sampleroomdbapp.database.dao

import androidx.lifecycle.LiveData
import com.example.sampleroomdbapp.AppDatabase
import com.example.sampleroomdbapp.Model.SampleNote

class DatabaseHelper(private val appDatabase: AppDatabase): DatabaseInterface{
    override fun getNoteList(): LiveData<List<SampleNote>?> {
        return appDatabase.getNoteDao().getAll()
    }

    override fun insertAll(sampleNote: SampleNote) {
        appDatabase.getNoteDao().insert(sampleNote)
    }

    override fun deleteNote(note: SampleNote) {
        appDatabase.getNoteDao().delete(note)
    }

    override fun deleteNotes(vararg notes: SampleNote) {
//        var noteArray = ArrayList<SampleNote>()
//        for(note in notes){
//            noteArray.add(note)
//        }
//        appDatabase.getNoteDao().delete(noteArray)
    }

}