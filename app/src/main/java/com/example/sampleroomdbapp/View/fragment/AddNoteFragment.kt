package com.example.sampleroomdbapp.View.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sampleroomdbapp.Model.SampleNote
import com.example.sampleroomdbapp.R
import com.example.sampleroomdbapp.Util.HelperUtility
import java.util.*


class AddNoteFragment: Fragment(), View.OnClickListener {

    private var title: EditText? = null
    private  var content:EditText? = null
    private var sampleNote: SampleNote? = null
    private lateinit var saveButton: Button
    private lateinit var addNoteToDb: AddNoteToDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_add_notes,container,false)
        initializeView(view)
        addNoteToDb = context as AddNoteToDb
        return view
    }

    private fun initializeView(view: View) {
        title = view.findViewById(R.id.et_title)
        content = view.findViewById<EditText>(R.id.et_content)
        saveButton = view.findViewById<Button>(R.id.but_save)
        saveButton.setOnClickListener(this)
    }

    interface AddNoteToDb{
        fun insertNoteToDB(note: SampleNote, editText: EditText)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.but_save->{
                if(!title!!.text.isNullOrEmpty() && !content!!.text.isNullOrEmpty()){
                    sampleNote = SampleNote(getRandomHexadecimalNumber(), content!!.text.toString(),title!!.text.toString())
                    addNoteToDb.insertNoteToDB(sampleNote!!, content!!)
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
