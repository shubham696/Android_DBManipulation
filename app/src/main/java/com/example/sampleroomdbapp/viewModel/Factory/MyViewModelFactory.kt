package com.example.sampleroomdbapp.viewModel.Factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleroomdbapp.view.activity.ShowAllNotes
import com.example.sampleroomdbapp.viewModel.ShowNoteViewModel
import com.example.sampleroomdbapp.database.dao.DatabaseHelper

class MyViewModelFactory(private val application: Application, val context: ShowAllNotes, val dbHelper: DatabaseHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowNoteViewModel(application, dbHelper) as T
    }

}
