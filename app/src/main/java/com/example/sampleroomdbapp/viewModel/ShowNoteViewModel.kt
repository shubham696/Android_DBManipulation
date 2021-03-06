package com.example.sampleroomdbapp.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.sampleroomdbapp.model.SampleNote
import com.example.sampleroomdbapp.database.dao.DatabaseHelper
import java.lang.Exception

class ShowNoteViewModel(val context: Application, private val dbHelper: DatabaseHelper) :ViewModel(){
    private var listOfNotesFromViewModel: LiveData<List<SampleNote>?>? = null

    init {
        setListOfNotes()
    }

    override fun onCleared() {
        super.onCleared()
    }


    private fun setListOfNotes(): LiveData<List<SampleNote>?>? {
            try {
                listOfNotesFromViewModel = dbHelper.getNoteList()!!
            }catch (e: Exception){
                e.printStackTrace()
            }
        return  listOfNotesFromViewModel
    }

    fun getListOfNotes(): LiveData<List<SampleNote>?>?{
        return listOfNotesFromViewModel
    }

    fun deleteNote(note: SampleNote): Boolean{
        try {
            dbHelper.deleteNote(note)
        }catch (e: Exception){
            e.printStackTrace()
            return false
        }
        return true
    }

    fun insertNotes(sampleNote: SampleNote) {
        try {
            dbHelper.insertAll(sampleNote = sampleNote)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun updateNote(sampleNote: SampleNote){
        try {
            dbHelper.updateNote(sampleNote)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

}