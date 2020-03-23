package com.figuredin.controllers.ui.notes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.figuredin.controllers.Controller.other.NetworkStateResource
import com.figuredin.controllers.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_new_note.view.*
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.android.synthetic.main.fragment_notes.view.*
import kotlinx.android.synthetic.main.fragment_notes.view.rv_list

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NoteListFragment : Fragment() {

    lateinit var viewModel : NoteViewModel
    private lateinit var auth: FirebaseAuth
    private val TAG = NoteListFragment::class.java.simpleName


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        viewModel = ViewModelProvider(this.activity!!).get(NoteViewModel::class.java)
        val layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        view.rv_list.layoutManager = layoutManager

        viewModel.geNote("")

        viewModel.observerGetNoteRequest().observe(viewLifecycleOwner, Observer {
            when(it?.status){
                NetworkStateResource.Status.ERROR->{
                    Log.d(TAG, "Error : ${it.message}")

                }

                NetworkStateResource.Status.LOADING->{
                    Log.d(TAG, "Saving...")

                }

                NetworkStateResource.Status.SUCCESS->{
                    val list = it.data
                    list?.let {
                        val adapter = NoteAdapter(it)
                        view.rv_list.adapter = adapter

                    }
                }
            }

        })




        view.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}
