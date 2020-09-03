package com.example.sampleroomdbapp.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleroomdbapp.Model.SampleNote
import com.example.sampleroomdbapp.dao.DatabaseHelper
import kotlinx.coroutines.launch
import java.lang.Exception

class TodoViewModel(val context: Application, private val dbHelper: DatabaseHelper) :ViewModel(){
    lateinit var listOfNotesFromViewModel: ArrayList<SampleNote>

    init {
        getListOfNotes()
    }

    override fun onCleared() {
        super.onCleared()
    }


    private fun getListOfNotes() {
        viewModelScope.launch {
            try {
                listOfNotesFromViewModel = dbHelper.getNoteList()!!
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

}