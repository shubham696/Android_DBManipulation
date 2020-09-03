package com.example.sampleroomdbapp.ViewModel.Factory

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleroomdbapp.View.activity.AddNotesActivity
import com.example.sampleroomdbapp.View.activity.ShowAddedNotes
import com.example.sampleroomdbapp.ViewModel.AddTodoViewModel
import com.example.sampleroomdbapp.dao.DatabaseHelper

class AddTodoVideoModelFactory(private val application: Application, val context: AddNotesActivity, val dbHelper: DatabaseHelper):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddTodoViewModel(application, dbHelper) as T
    }

}