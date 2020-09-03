package com.example.sampleroomdbapp.View.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleroomdbapp.AppDatabase
import com.example.sampleroomdbapp.MainApplication
import com.example.sampleroomdbapp.Model.SampleNote
import com.example.sampleroomdbapp.R
import com.example.sampleroomdbapp.View.adapter.SavedNotesAdapter
import com.example.sampleroomdbapp.ViewModel.Factory.MyViewModelFactory
import com.example.sampleroomdbapp.ViewModel.TodoViewModel
import com.example.sampleroomdbapp.dao.DatabaseHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShowAddedNotes : AppCompatActivity(), View.OnClickListener{

    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var listOfNotesRecyclerView: RecyclerView
    private lateinit var dbInstance: AppDatabase
    private var listOfSampleNote: List<SampleNote?>? = null
    private lateinit var emptyMessage: TextView
    private lateinit var adapter: SavedNotesAdapter
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()

        adapter.setData(todoViewModel.listOfNotesFromViewModel)
    }

    private fun initializeViews() {
        floatingActionButton = findViewById(R.id.fab)
        listOfNotesRecyclerView = findViewById(R.id.recycler_view)
        emptyMessage = findViewById<TextView>(R.id.tv__empty)
        dbInstance = MainApplication().getDBInstance(this)

        adapter = SavedNotesAdapter(this,listOfSampleNote)
        floatingActionButton.setOnClickListener(this)
        listOfNotesRecyclerView.layoutManager = LinearLayoutManager(this)
        listOfNotesRecyclerView.adapter = adapter

        val dbHelper = DatabaseHelper(dbInstance)

        todoViewModel = ViewModelProvider (
            this,
            MyViewModelFactory(
                this.application,
                this,
                dbHelper
            )
        ).get(TodoViewModel::class.java)
//        RetrieveTask(this).execute()
    }

    private fun getListOfNotes() {
        dbInstance.getNoteDao()
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

//    private class RetrieveTask internal constructor(context: ShowAddedNotes) :
//        AsyncTask<Void?, Void?, List<Note?>?>() {
//        private val activityReference: WeakReference<ShowAddedNotes>
//        override fun doInBackground(vararg voids: Void?): List<Note?>? {
//            return if (activityReference.get() != null) activityReference.get()!!.dbInstance.getNoteDao()
//                .getAll() else null
//        }
//
//        override fun onPostExecute(notes: List<Note?>?) {
//            if (notes != null && notes.isNotEmpty()) {
//                activityReference.get()?.listOfNote = notes!!
//
//                // hides empty text view
//                activityReference.get()?.emptyMessage!!.setVisibility(View.GONE)
//
//                // create and set the adapter on RecyclerView instance to display list
////                activityReference.get()?.adapter = SavedNotesAdapter(activityReference.get()!!,notes)
////                activityReference.get().recyclerView.setAdapter(activityReference.get().notesAdapter)
//                activityReference.get()?.adapter!!.setData(notes)
//            }
//        }
//
//        // only retain a weak reference to the activity
//        init {
//            activityReference = WeakReference(context!!)
//        }
//    }
//
//    override fun onClick(v: View?) {
//        when(v!!.id){
//            R.id.fab->{
//                startActivity(Intent(this@ShowAddedNotes,AddNotesActivity::class.java))
//            }
//        }
//    }
}