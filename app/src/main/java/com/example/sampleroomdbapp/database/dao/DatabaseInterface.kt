package com.example.sampleroomdbapp.database.dao

import androidx.lifecycle.LiveData
import com.example.sampleroomdbapp.model.SampleNote

interface DatabaseInterface {
    fun getNoteList(): LiveData<List<SampleNote>?>
    fun insertAll(sampleNote: SampleNote)
    fun deleteNote(note: SampleNote)
    fun deleteNotes( vararg note: SampleNote)
    fun updateNote(note: SampleNote)
}