package com.example.sampleroomdbapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo_Note")
data class SampleNote(@PrimaryKey  val note_id: String, @ColumnInfo(name = "note_content") val content: String, val title: String) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SampleNote> {
        override fun createFromParcel(parcel: Parcel): SampleNote {
            return SampleNote(parcel)
        }

        override fun newArray(size: Int): Array<SampleNote?> {
            return arrayOfNulls(size)
        }
    }
}
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
