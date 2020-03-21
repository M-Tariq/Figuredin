package com.figuredin.controllers.Controller.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.figuredin.controllers.Controller.Model.NoteModel
import com.figuredin.controllers.Controller.other.NetworkStateResource

class NoteViewModel : ViewModel(){

    val TAG = NoteViewModel::class.java.simpleName
    var noteRepository = NoteRepository()

    private val saveRequestStatus = MediatorLiveData<NetworkStateResource<String?>>()

    private val myNotesStatus = MediatorLiveData<NetworkStateResource<MutableList<NoteModel>?>>()


    fun saveInDb(userId : String, note: NoteModel){
        saveRequestStatus.value = NetworkStateResource.loading()

        noteRepository.addNote(userId, note).addOnSuccessListener {
            saveRequestStatus.value = NetworkStateResource.success("Success", it.id)
        }.addOnFailureListener {
            saveRequestStatus.value = NetworkStateResource.error(it.message)
        }
    }


    fun geNote(id: String){
        myNotesStatus.value = NetworkStateResource.loading()
        noteRepository.getNote(id).addOnSuccessListener {
            val list = arrayListOf<NoteModel>()
            for (document in it.documents) {
                val item = document.toObject(NoteModel::class.java)
                Log.d(TAG, "Item : $item")
                item?.let {
                    list.add(it)
                }
            }
            myNotesStatus.value = NetworkStateResource.success("found", list)
        }.addOnFailureListener {
            var msg = "Something went wrong"
            it.message?.let {
                msg = it
            }
            myNotesStatus.value = NetworkStateResource.error(msg)
        }
    }


    fun observerNoteRequest(): LiveData<NetworkStateResource<String?>?> {
        return saveRequestStatus
    }

    fun observerGetNoteRequest(): LiveData<NetworkStateResource<MutableList<NoteModel>?>> {
        return myNotesStatus
    }




}