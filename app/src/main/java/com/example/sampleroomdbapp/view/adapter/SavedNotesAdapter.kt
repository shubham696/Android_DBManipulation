package com.example.sampleroomdbapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleroomdbapp.model.SampleNote
import com.example.sampleroomdbapp.R
import com.example.sampleroomdbapp.view.interfaces.SampleNoteInterface
import kotlinx.android.synthetic.main.note_item.view.*


class SavedNotesAdapter(val context: Context, list: List<SampleNote?>?): RecyclerView.Adapter<SavedNotesAdapter.ViewHolder>() {

    private var notesList: List<SampleNote?>? = null
    private var onLogClickInterface: onLongClick
    private lateinit var onSampleNoteClick: SampleNoteInterface
    init {
        this.notesList = list
        onLogClickInterface = context as onLongClick
        onSampleNoteClick = context as SampleNoteInterface
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent,false))
    }

    fun setData(list: List<SampleNote?>?){
        notesList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return notesList?.size?:0
    }


    interface onLongClick{
        fun deleteNote(note: SampleNote?)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.isSelected = true
        holder.title.text = notesList?.get(position)?.title
        holder.content.text = notesList?.get(position)?.content
        holder.noteCard.setOnLongClickListener(View.OnLongClickListener { view ->
            onLogClickInterface.deleteNote(notesList?.get(position))
            true
        })

        holder.noteCard.setOnClickListener(View.OnClickListener { view ->
            onSampleNoteClick.sampleNoteClicked(notesList?.get(position)!!)
        })
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title = view.tv_title
        val content = view.item_text
        val noteCard = view.imageView
    }

}