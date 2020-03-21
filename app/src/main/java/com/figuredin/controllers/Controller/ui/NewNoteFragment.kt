package com.figuredin.controllers.Controller.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.figuredin.controllers.Controller.Model.NoteModel
import com.figuredin.controllers.Controller.other.NetworkStateResource
import com.figuredin.controllers.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_new_note.*
import kotlinx.android.synthetic.main.fragment_new_note.view.*


class NewNoteFragment : Fragment() {

    lateinit var viewModel : NoteViewModel
    private lateinit var auth: FirebaseAuth
    private val TAG = NewNoteFragment::class.java.simpleName



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_note, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        viewModel = ViewModelProvider(this.activity!!).get(NoteViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observerNoteRequest().observe(viewLifecycleOwner, Observer {
            when(it?.status){
                NetworkStateResource.Status.ERROR->{

                }

                NetworkStateResource.Status.LOADING->{

                }

                NetworkStateResource.Status.SUCCESS->{

                }
            }

        })

        view.btn_submit.setOnClickListener {
            val title = view.et_title.text.toString()
            val desc = view.et_desc.text.toString()

            if (auth.currentUser==null){
                return@setOnClickListener
            }

            val id = auth.currentUser!!.uid

            if (title.trim().isNotEmpty()&&desc.trim().isNotEmpty()){
                val note = NoteModel(title, desc)
                viewModel.saveInDb(id, note)
            }


        }







    }
}
