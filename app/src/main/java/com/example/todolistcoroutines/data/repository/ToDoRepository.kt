package com.example.todolistcoroutines.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.todolistcoroutines.data.dao.ToDoDao
import com.example.todolistcoroutines.model.ToDo
import org.koin.core.component.KoinComponent


interface ToDoRepository {
    suspend fun addNewTodo(todo: ToDo)

    fun getAllToDo(): LiveData<List<ToDo>>

    suspend fun getToDoById(id: Int): ToDo

    suspend fun deleteTodo(todo: ToDo)

    suspend fun updateTodo(todo: ToDo)

    suspend fun deleteAll()
}

class ToDoRepositoryImpl(private val dao: ToDoDao, private val context: Context) : ToDoRepository,
    KoinComponent {


    override suspend fun addNewTodo(todo: ToDo) {
        Log.d("ToDo Repository", "addNewTodo Working with $todo")
        dao.insert(todo)
    }

    override fun getAllToDo(): LiveData<List<ToDo>> {
        Log.d("ToDo Repository", "getAllToDo Working...")
        return dao.getAllTodos()
    }

    override suspend fun getToDoById(id: Int): ToDo {
        Log.d("ToDo Repository", "getAllToDo Working...")
        return dao.getTodo(id)
    }

    override suspend fun deleteTodo(todo: ToDo) {
        Log.d("ToDo Repository", "deleteTodo Working with $todo")
        dao.delete(todo)

    }

    override suspend fun updateTodo(todo: ToDo) {
        Log.d("ToDo Repository", "updateTodo Working with $todo")
        dao.update(todo)

    }

    override suspend fun deleteAll() {
        Log.d("ToDo Repository", "deleteAll Working...")
        dao.nukeTable()

    }


}