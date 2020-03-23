package com.figuredin.controllers.Controller.Model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class NoteModel(var noteId : String?, var userId : String?, var title : String?, var noteDesc : String?, var visiblity : Boolean,
                     @ServerTimestamp var createdAt : Date?, @ServerTimestamp var modifiedAt : Date?){
    constructor(userId: String, title: String, noteDesc: String) : this (null, userId, title, noteDesc, true, null, null)
    constructor() : this(null, null, null, null, true, null, null)
}