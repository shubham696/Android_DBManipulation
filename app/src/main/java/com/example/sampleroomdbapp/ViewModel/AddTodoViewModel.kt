package com.example.sampleroomdbapp.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleroomdbapp.Model.SampleNote
import com.example.sampleroomdbapp.dao.DatabaseHelper
import kotlinx.coroutines.launch
import java.lang.Exception

class AddTodoViewModel(val context: Application, private val dbHelper: DatabaseHelper) :ViewModel(){
    var listOfNotesFromViewModel: List<SampleNote?>? = null

    init {
    }

    override fun onCleared() {
        super.onCleared()
    }


     fun insertNotes(sampleNote: SampleNote) {
        viewModelScope.launch {
            try {
                dbHelper.insertAll(sampleNote = sampleNote)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

}