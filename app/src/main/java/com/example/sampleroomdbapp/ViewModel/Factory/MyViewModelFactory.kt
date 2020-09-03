package com.example.sampleroomdbapp.ViewModel.Factory

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleroomdbapp.AppDatabase
import com.example.sampleroomdbapp.View.activity.ShowAddedNotes
import com.example.sampleroomdbapp.ViewModel.TodoViewModel
import com.example.sampleroomdbapp.dao.DatabaseHelper

class MyViewModelFactory(private val application: Application, val context: ShowAddedNotes, val dbHelper: DatabaseHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodoViewModel(application, dbHelper) as T
    }

}
