package com.example.sampleroomdbapp.ViewModel.Factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleroomdbapp.View.fragment.AddNoteFragment
import com.example.sampleroomdbapp.ViewModel.AddTodoViewModel
import com.example.sampleroomdbapp.database.dao.DatabaseHelper

class AddTodoVideoModelFactory(private val application: Application, val context: AddNoteFragment, val dbHelper: DatabaseHelper):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddTodoViewModel(application, dbHelper) as T
    }

}