package com.example.sampleroomdbapp.Model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo_Note")
data class SampleNote(@PrimaryKey  val note_id: String, @ColumnInfo(name = "note_content") val content: String, val title: String)
//{

//    @PrimaryKey
//    @NonNull
//    @ColumnInfo(name = "note_id")
//    private var note_id = 0
//
//    @ColumnInfo(name = "note_content")
//    private var content = ""
//
//    @ColumnInfo(name = "title")
//    private var title = ""

//    init{
//        this.note_id = note_id
//        this.content = content
//        this.title = title
//    }
//
//    fun getNote_id(): Int{
//        return note_id
//    }
//
//    fun setNote_id(value: Int){
//        this.note_id = value
//    }
//
//    fun getContent(): String{
//        return content
//    }
//
//    fun setContent(value: String){
//        this.content = value
//    }
//
//    fun getTitle(): String{
//        return title
//    }
//
//    fun setTitle(value: String){
//        this.title = value
//    }

//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as SampleNote
//
//        if (note_id != other.note_id) return false
//        if (title != other.title) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = note_id
//        result = 31 * result + title.hashCode()
//        return result
//    }
//
//    override fun toString(): String {
//        return "Note(note_id=$note_id, content='$content', title='$title')"
//    }
//
//
//}
