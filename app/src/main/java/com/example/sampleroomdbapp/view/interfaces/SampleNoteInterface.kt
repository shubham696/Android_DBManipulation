package com.example.sampleroomdbapp.view.interfaces

import android.widget.EditText
import com.example.sampleroomdbapp.model.SampleNote

interface SampleNoteInterface {
    fun sampleNoteClicked(sampleNote: SampleNote)
    fun updateSampleNote(sampleNote: SampleNote, editText: EditText)
}