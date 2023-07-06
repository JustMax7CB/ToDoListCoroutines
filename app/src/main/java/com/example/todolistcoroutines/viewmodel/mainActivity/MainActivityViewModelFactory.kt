package com.example.todolistcoroutines.viewmodel.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolistcoroutines.data.dao.ToDoDao
import com.example.todolistcoroutines.data.repository.ToDoRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class MainActivityViewModelFactory(val toDoRepository: ToDoRepository) :
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(toDoRepository) as T
        }
        throw IllegalArgumentException("Unknown view model")
    }
}