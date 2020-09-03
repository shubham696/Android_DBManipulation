package com.example.sampleroomdbapp.View.activity

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sampleroomdbapp.AppDatabase
import com.example.sampleroomdbapp.MainApplication
import com.example.sampleroomdbapp.Model.SampleNote
import com.example.sampleroomdbapp.R
import com.example.sampleroomdbapp.ViewModel.AddTodoViewModel
import com.example.sampleroomdbapp.ViewModel.Factory.AddTodoVideoModelFactory
import com.example.sampleroomdbapp.dao.DatabaseHelper
import java.lang.ref.WeakReference


class AddNotesActivity: AppCompatActivity(), View.OnClickListener {

    private var title: EditText? = null
    private  var content:EditText? = null
    private var appDatabase: AppDatabase? = null
    private var sampleNote: SampleNote? = null
    private lateinit var saveButton: Button
    lateinit var dbHelper: DatabaseHelper
    lateinit var addTodoViewModel: AddTodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
        appDatabase = MainApplication().getDBInstance(this)
        initializeView()

    }

    private fun initializeView() {
        title = findViewById(R.id.et_title)
        content = findViewById<EditText>(R.id.et_content)
        saveButton = findViewById<Button>(R.id.but_save)
        saveButton.setOnClickListener(this)
        dbHelper = DatabaseHelper(appDatabase!!)
        addTodoViewModel = ViewModelProvider(
            this,
            AddTodoVideoModelFactory(
                this.application,
                this,
                dbHelper
            )
        ).get(AddTodoViewModel::class.java)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.but_save->{
                if(!title!!.text.isNullOrEmpty() && !content!!.text.isNullOrEmpty()){
                    sampleNote = SampleNote(Math.random().toInt() * 17, content!!.text.toString(),title!!.text.toString())
//                    InsertTask(this@AddNotesActivity, note!!).execute()
                    addTodoViewModel.insertNotes(sampleNote!!)
                }else{
                    Toast.makeText(this,"Please Enter Title/Content",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private suspend fun insertIntoDB(sampleNote: SampleNote) {
        dbHelper?.insertAll(sampleNote)
    }

    private class InsertTask internal constructor(context: AddNotesActivity?, sampleNote: SampleNote) :
        AsyncTask<Void?, Void?, Boolean>() {
        private val activityReference: WeakReference<AddNotesActivity>
        private val sampleNote: SampleNote

        // doInBackground methods runs on a worker thread
        override fun doInBackground(vararg p0: Void?): Boolean? {
            activityReference.get()?.appDatabase?.getNoteDao()?.insert(sampleNote)
            return true
        }

        // onPostExecute runs on main thread
        override fun onPostExecute(bool: Boolean) {
            if (bool) {
                activityReference.get()?.setResult(sampleNote,1)
            }
        }

        // only retain a weak reference to the activity
        init {
            activityReference = WeakReference(context!!)
            this.sampleNote = sampleNote
        }
    }

    private fun setResult(sampleNote: SampleNote, flag: Int) {
        setResult(flag, Intent().putExtra("note", sampleNote.toString()))
        finish()
    }

}
