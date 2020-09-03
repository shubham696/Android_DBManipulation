package com.example.sampleroomdbapp.dao

import com.example.sampleroomdbapp.Model.SampleNote

interface DatabaseInterface {
    suspend fun getNoteList(): ArrayList<SampleNote>?
    suspend fun insertAll(sampleNote: SampleNote)
}