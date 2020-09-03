package com.example.sampleroomdbapp.dao

import androidx.room.*
import com.example.sampleroomdbapp.Model.SampleNote
import com.example.sampleroomdbapp.Util.Constants

@Dao
public abstract class NoteDao{

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM "+ Constants.TABLE_NAME_NOTE)
    abstract suspend fun getAll(): ArrayList<SampleNote>?

    @Insert
    abstract fun insert(sampleNote: SampleNote)

    @Update
    abstract fun update(sampleNote: SampleNote)

    @Delete
    abstract fun delete(sampleNote: SampleNote)

    @Delete
    abstract fun delete(vararg sampleNote: SampleNote)

}