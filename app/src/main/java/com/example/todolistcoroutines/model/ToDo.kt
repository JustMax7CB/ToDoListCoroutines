package com.example.todolistcoroutines.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todoDatabase")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")  val id: Int = 0,
    @ColumnInfo(name = "title") val title: String = "item title",
    @ColumnInfo(name = "description") val description: String = "item description",
    @ColumnInfo(name = "itemDone") val done: Boolean = false
)
