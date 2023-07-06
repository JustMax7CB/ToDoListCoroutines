package com.example.todolistcoroutines.viewmodel.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistcoroutines.data.repository.ToDoRepository
import com.example.todolistcoroutines.model.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivityViewModel(private val toDoRepository: ToDoRepository) : ViewModel() {

    private val TAG = "MainActivityVM"
    private lateinit var job: Job

    var _todoLiveData: MutableLiveData<List<ToDo>> = MutableLiveData()
    val toDoLiveData: LiveData<List<ToDo>> get() = _todoLiveData


    fun getTodoById(id: Int) {
        viewModelScope.launch {
            toDoRepository.getToDoById(id)
        }
    }

    fun addNewTodo(todo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            toDoRepository.addNewTodo(todo)
        }
    }

    fun deleteTodo(todo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            toDoRepository.deleteTodo(todo)
        }
    }

    fun clearAllToDo() {
        viewModelScope.launch(Dispatchers.IO) {
            toDoRepository.deleteAll()
        }
    }


    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}