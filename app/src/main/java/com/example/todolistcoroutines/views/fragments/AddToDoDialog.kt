package com.example.todolistcoroutines.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.todolistcoroutines.R
import com.example.todolistcoroutines.databinding.FragmentAddToDoBinding

class AddToDoDialog : DialogFragment() {

    private lateinit var binding: FragmentAddToDoBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_to_do, container, false)
        return binding.root
    }


    fun addNewToDo() {
        binding.buttonAddToDo.setOnClickListener {

        }
    }
}