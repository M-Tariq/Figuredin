package com.figuredin.controllers.Controller.ui

import com.figuredin.controllers.Controller.Model.NoteModel
import com.figuredin.controllers.Controller.other.Constant
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class NoteRepository {

    val TAG = NoteRepository::class.java.simpleName
    var firestoreDb = FirebaseFirestore.getInstance()


    fun addNote(userId : String, note: NoteModel): Task<DocumentReference> {
        val documentReference = firestoreDb.collection("${Constant.USER_COLLECTION}/${userId}/${Constant.FolderCollection}")
        return documentReference.add(note)
    }

    fun getNote(id : String): Task<QuerySnapshot> {
        val documentReference = firestoreDb.collection("${Constant.USER_COLLECTION}/$id/${Constant.FolderCollection}")
        return documentReference.get()
    }



}