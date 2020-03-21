package com.figuredin.controllers.Controller.Model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class NoteModel(var noteId : String?, var title : String, var noteDesc : String,
                     @ServerTimestamp var createdAt : Date?, @ServerTimestamp var modifiedAt : Date?){
    constructor(title: String, noteDesc: String) : this (null, title, noteDesc, null, null)
}