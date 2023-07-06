package com.example.todolistcoroutines.views.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistcoroutines.R
import com.example.todolistcoroutines.data.repository.ToDoRepository
import com.example.todolistcoroutines.databinding.ActivityMainBinding
import com.example.todolistcoroutines.model.ToDo
import com.example.todolistcoroutines.viewmodel.mainActivity.MainActivityViewModel
import com.example.todolistcoroutines.views.adapters.ToDoAdapter
import com.example.todolistcoroutines.views.fragments.AddToDoDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MainActivity : AppCompatActivity(), KoinComponent {

    private val TAG = "MainActivity"

    private val mainViewModel: MainActivityViewModel by viewModel()

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding
    private val toDoRepo: ToDoRepository by inject()

    private lateinit var toDoAdapter: ToDoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        setAdapter()
        mainViewModel.toDoLiveData.observe(this) { toDos ->
            Log.d(TAG, toDos.toString())
            toDoAdapter.setItems(toDos)
        }


        toDoRepo.getAllToDo().observe(this) {
            mainViewModel._todoLiveData.postValue(it)
        }


        binding.floatingButtonAddNewItem.setOnClickListener {
            AddToDoDialog().show(
                supportFragmentManager,
                "AddToDoDialog"
            )
        }
    }

    private fun addNewTodo() {
        mainViewModel.addNewTodo(
            ToDo(
                title = "New Item",
                description = "item created via button click"
            )
        )
    }


    private fun setAdapter() {
        toDoAdapter = ToDoAdapter(mainViewModel::deleteTodo)
        binding.recyclerView.adapter = toDoAdapter
        with(binding.recyclerView) {
            val layout_manager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            layoutManager = layout_manager
            addItemDecoration(DividerItemDecoration(baseContext, layout_manager.orientation))

        }
    }
}