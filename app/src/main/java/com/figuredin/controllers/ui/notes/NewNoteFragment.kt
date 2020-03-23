package com.figuredin.controllers.ui.notes

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.figuredin.controllers.Controller.Model.NoteModel
import com.figuredin.controllers.Controller.other.NetworkStateResource
import com.figuredin.controllers.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_new_note.view.*


class NewNoteFragment : Fragment() {

    lateinit var viewModel : NoteViewModel
    private lateinit var auth: FirebaseAuth
    private val TAG = NewNoteFragment::class.java.simpleName
    private lateinit var rootView : View



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_new_note, container, false)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setHasOptionsMenu(true)

        viewModel = ViewModelProvider(this.activity!!).get(NoteViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observerNoteRequest().observe(viewLifecycleOwner, Observer {
            when(it?.status){
                NetworkStateResource.Status.ERROR->{
                    Log.d(TAG, "Error : ${it.message}")

                }

                NetworkStateResource.Status.LOADING->{
                    Log.d(TAG, "Saving...")

                }

                NetworkStateResource.Status.SUCCESS->{
                    Log.d(TAG, "Saved")
                    view.et_title.setText("")
                    view.et_desc.setText("")
                    findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                }
            }

        })



    }

    private fun post(){
        val title = rootView.et_title.text.toString()
        val desc = rootView.et_desc.text.toString()

        if (auth.currentUser==null){
            return
        }

        val id = auth.currentUser!!.uid

        if (title.trim().isNotEmpty()&&desc.trim().isNotEmpty()){
            val note = NoteModel(id, title, desc)
            viewModel.saveInDb(note)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.note_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.action_save){
            post()
        }

        return super.onOptionsItemSelected(item)
    }
}
