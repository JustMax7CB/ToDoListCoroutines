package com.example.todolistcoroutines.di

import android.app.Application
import androidx.room.Room
import com.example.todolistcoroutines.data.dao.ToDoDao
import com.example.todolistcoroutines.data.database.ToDoDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


var databaseModule = module {
    fun provideDatabase(application: Application): ToDoDatabase {
        return Room.databaseBuilder(application, ToDoDatabase::class.java, "todoDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideToDoDao(database: ToDoDatabase): ToDoDao {
        return database.toDoDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideToDoDao(get()) }
}

