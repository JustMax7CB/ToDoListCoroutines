package com.example.todolistcoroutines.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todolistcoroutines.model.ToDo

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todoDatabase")
    fun getAllTodos(): LiveData<List<ToDo>>

    @Query("SELECT * FROM todoDatabase WHERE id = :itemId")
    suspend fun getTodo(itemId: Int) : ToDo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg todo: ToDo)

    @Delete
    suspend fun delete(todo: ToDo)

    @Update
    suspend fun update(todo: ToDo)

    @Query("DELETE FROM todoDatabase")
    suspend fun nukeTable()
}