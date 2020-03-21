package com.figuredin.controllers.Controller.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.figuredin.controllers.R

import kotlinx.android.synthetic.main.activity_note.*

class Note : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setSupportActionBar(toolbar)
        ViewModelProvider(this).get(NoteViewModel::class.java)


    }

}
