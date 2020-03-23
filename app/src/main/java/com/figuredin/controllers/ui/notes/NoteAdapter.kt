package com.figuredin.controllers.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.figuredin.controllers.Controller.Model.NoteModel
import com.figuredin.controllers.Controller.other.dateToString
import com.figuredin.controllers.R
import kotlinx.android.synthetic.main.item_notes.view.*

class NoteAdapter(var list: MutableList<NoteModel>) : RecyclerView.Adapter<NoteAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(noteModel: NoteModel){
            noteModel.title?.let {
                itemView.tv_title.text = it
            }

            noteModel.noteDesc?.let {
                itemView.tv_desc.text = it
            }

            noteModel.createdAt?.let {
                itemView.tv_time.text = it.dateToString("dd-MM-yy HH:mm")
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }
}