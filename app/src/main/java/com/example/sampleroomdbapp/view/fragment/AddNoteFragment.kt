package com.example.sampleroomdbapp.view.fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sampleroomdbapp.model.SampleNote
import com.example.sampleroomdbapp.R
import com.example.sampleroomdbapp.view.interfaces.SampleNoteInterface
import kotlinx.android.synthetic.main.activity_add_notes.view.*
import java.util.*


class AddNoteFragment: Fragment(), View.OnClickListener {

    private var title: EditText? = null
    private  var content:EditText? = null
    private var sampleNote: SampleNote? = null
    private lateinit var saveButton: Button
    private lateinit var addNoteToDb: AddNoteToDb
    private lateinit var onBack: OnBackPressed
    private var editNote: SampleNote? = null
    private lateinit var updateNoteListener: SampleNoteInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_add_notes,container,false)
        readBundle()
        initializeView(view)
        addNoteToDb = context as AddNoteToDb
        onBack = context as OnBackPressed
        return view
    }

    private fun readBundle() {
        arguments?.let {
            if(it.getParcelable<SampleNote>("currentNote") != null){
                updateNoteListener = context as SampleNoteInterface
                editNote = it.getParcelable<SampleNote>("currentNote")!!
            }
        }
    }

    private fun initializeView(view: View) {
        title = view.et_title
        content = view.et_content
        saveButton = view.but_save
        saveButton.setOnClickListener(this)

        if(editNote != null){
            title!!.setText(editNote?.title.toString())
            content!!.setText(editNote?.content.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                onBack.onBack()
                true
            } else false
        }
    }

    interface AddNoteToDb{
        fun insertNoteToDB(note: SampleNote, editText: EditText)
    }

    interface OnBackPressed{
        fun onBack()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.but_save->{
                if(!title!!.text.isNullOrEmpty() && !content!!.text.isNullOrEmpty()){
                    if(editNote != null){
                        sampleNote = SampleNote(editNote?.note_id!!, content!!.text.toString(),title!!.text.toString())
                        updateNoteListener.updateSampleNote(sampleNote!!, content!!)
                    }else{
                        sampleNote = SampleNote(getRandomHexadecimalNumber(), content!!.text.toString(),title!!.text.toString())
                        addNoteToDb.insertNoteToDB(sampleNote!!, content!!)
                    }
                }else{
                    Toast.makeText(this.context,"Please Enter Title/Content",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getRandomHexadecimalNumber(): String {
        val zeros = "000000"
        val rnd = Random()
        var s = Integer.toString(rnd.nextInt(0X1000000), 16)
        s = zeros.substring(s.length) + s
        return s
    }

}
