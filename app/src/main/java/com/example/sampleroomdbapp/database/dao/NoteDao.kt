package com.example.sampleroomdbapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sampleroomdbapp.Model.SampleNote
import com.example.sampleroomdbapp.Util.Constants

@Dao
interface NoteDao{

    @Query("SELECT * FROM "+ Constants.TABLE_NAME_NOTE)
    fun getAll(): LiveData<List<SampleNote>?>

    @Insert
    fun insert(sampleNote: SampleNote)

    @Update
    fun update(sampleNote: SampleNote)

    @Delete
    fun delete(sampleNote:  SampleNote)

    @Delete
    fun delete(vararg sampleNote: SampleNote)

}