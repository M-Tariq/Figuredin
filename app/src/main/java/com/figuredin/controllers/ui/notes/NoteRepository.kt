package com.figuredin.controllers.ui.notes

import com.figuredin.controllers.Controller.Model.NoteModel
import com.figuredin.controllers.Controller.other.Constant
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.SetOptions

class NoteRepository {

    val TAG = NoteRepository::class.java.simpleName
    var firestoreDb = FirebaseFirestore.getInstance()


    fun addNote(note: NoteModel): Task<DocumentReference> {
        val documentReference = firestoreDb.collection(Constant.FolderCollection)
        return documentReference.add(note)
    }

    fun updateNoteId(noteId : String){
        val documentReference = firestoreDb.collection(Constant.FolderCollection).document(noteId)
        documentReference.update("noteId", noteId)
    }

    fun getNote(id : String): Task<QuerySnapshot> {
        val documentReference = firestoreDb.collection(Constant.FolderCollection)
        return documentReference.get()
    }



}