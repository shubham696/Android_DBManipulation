package com.example.sampleroomdbapp.View.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleroomdbapp.Model.SampleNote
import com.example.sampleroomdbapp.R


class SavedNotesAdapter(val context: Context, list: List<SampleNote?>?): RecyclerView.Adapter<SavedNotesAdapter.ViewHolder>() {

    private var notesList: List<SampleNote?>? = null
    init {
        this.notesList = list
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.content.isSelected = true
        holder.title.isSelected = true
        holder.title.text = notesList?.get(position)?.title
        holder.content.text = notesList?.get(position)?.content
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.tv_title)
        val content = view.findViewById<TextView>(R.id.item_text)
    }

}