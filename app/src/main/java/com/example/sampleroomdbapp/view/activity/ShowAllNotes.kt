package com.example.sampleroomdbapp.view.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleroomdbapp.AppDatabase
import com.example.sampleroomdbapp.MainApplication
import com.example.sampleroomdbapp.R
import com.example.sampleroomdbapp.database.dao.DatabaseHelper
import com.example.sampleroomdbapp.model.SampleNote
import com.example.sampleroomdbapp.util.HelperUtility
import com.example.sampleroomdbapp.util.PrefNames
import com.example.sampleroomdbapp.view.adapter.SavedNotesAdapter
import com.example.sampleroomdbapp.view.fragment.AddNoteFragment
import com.example.sampleroomdbapp.view.interfaces.SampleNoteInterface
import com.example.sampleroomdbapp.viewModel.Factory.MyViewModelFactory
import com.example.sampleroomdbapp.viewModel.ShowNoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.header_layout.*


class ShowAllNotes : AppCompatActivity(), View.OnClickListener, SavedNotesAdapter.onLongClick, AddNoteFragment.AddNoteToDb, AddNoteFragment.OnBackPressed, SampleNoteInterface{

    private val TAG = "showaddednotes"
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var listOfNotesRecyclerView: RecyclerView
    private lateinit var dbInstance: AppDatabase
    private var listOfSampleNote: List<SampleNote?>? = null
    private lateinit var emptyMessage: TextView
    private lateinit var adapter: SavedNotesAdapter
    private lateinit var todoViewModel: ShowNoteViewModel
    private lateinit var notesLiveData: LiveData<List<SampleNote>>
    private lateinit var addShowFragment: FrameLayout
    private lateinit var pre: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pre = getSharedPreferences(PrefNames.PREF_NAME, Context.MODE_PRIVATE)
        if(!pre.getBoolean(PrefNames.DARK_THEME,true)){
            setTheme(R.style.DarkTheme)
        }else{
            setTheme(R.style.WhiteTheme);
        }
        setContentView(R.layout.activity_main)
        initializeViews()

        todoViewModel.getListOfNotes()?.observe(this, Observer { notes->
            if(notes!!.isNotEmpty()){
                listOfNotesRecyclerView.visibility = View.VISIBLE
                emptyMessage.visibility = View.GONE
                adapter.setData(notes as List<SampleNote?>)
            }else{
                emptyMessage.visibility = View.VISIBLE
                listOfNotesRecyclerView.visibility = View.GONE
            }
        })

    }

    private fun initializeViews() {
        floatingActionButton = fab
        listOfNotesRecyclerView = recycler_view
        emptyMessage = tv__empty
        addShowFragment = addNoteFragment
        back_button.visibility = View.GONE
        header_title.text = resources.getString(R.string.notes)
        set_theme.setOnClickListener(this)

        dbInstance = MainApplication().getDBInstance(this)

        adapter = SavedNotesAdapter(this@ShowAllNotes,listOfSampleNote)
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
        ).get(ShowNoteViewModel::class.java)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(floatingActionButton.visibility == View.GONE)
            floatingActionButton.visibility = View.VISIBLE
    }



    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.fab->{
                addShowFragment.visibility = View.VISIBLE
                floatingActionButton.visibility = View.GONE
                val addNotesActivity = AddNoteFragment()
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.addNoteFragment, addNotesActivity, "addNotes")
                fragmentTransaction.addToBackStack("addNotes")
                fragmentTransaction.commit()
            }
            R.id.set_theme->{
                if(pre.getBoolean(PrefNames.DARK_THEME,true)){
                    pre.edit().putBoolean(PrefNames.DARK_THEME,false).apply()
                    setTheme(R.style.WhiteTheme)
                    finish()
                    startActivity(Intent(this@ShowAllNotes,ShowAllNotes::class.java))
                }else{
                    pre.edit().putBoolean(PrefNames.DARK_THEME,true).apply()
                    setTheme(R.style.DarkTheme)
                    finish()
                    startActivity(Intent(this@ShowAllNotes,ShowAllNotes::class.java))
                }
            }
        }
    }

    override fun deleteNote(note: SampleNote?) {
        if(todoViewModel.deleteNote(note!!)){
            Toast.makeText(this,"Note deleted",Toast.LENGTH_SHORT)
        }
    }

    override fun insertNoteToDB(note: SampleNote, editText: EditText) {
        todoViewModel.insertNotes(note)
        floatingActionButton.visibility = View.VISIBLE
        HelperUtility(this).hideKeyBoard(editText)
        supportFragmentManager.popBackStackImmediate()
    }

    override fun onBack() {
        floatingActionButton.visibility = View.VISIBLE
        supportFragmentManager.popBackStackImmediate()
    }

    override fun sampleNoteClicked(currentNote: SampleNote) {
        addShowFragment.visibility = View.VISIBLE
        floatingActionButton.visibility = View.GONE
        val addNotesActivity = AddNoteFragment()
        val bundle = Bundle()
        bundle.putParcelable("currentNote",currentNote)
        addNotesActivity.arguments = bundle
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.addNoteFragment, addNotesActivity, "editNote")
        fragmentTransaction.addToBackStack("editNote")
        fragmentTransaction.commit()
    }

    override fun updateSampleNote(sampleNote: SampleNote, editText: EditText) {
        todoViewModel.updateNote(sampleNote)
        floatingActionButton.visibility = View.VISIBLE
        HelperUtility(this).hideKeyBoard(editText)
        supportFragmentManager.popBackStackImmediate()
    }
}

