package com.example.todolistcoroutines.di

import android.content.Context
import com.example.todolistcoroutines.data.dao.ToDoDao
import com.example.todolistcoroutines.data.repository.ToDoRepository
import com.example.todolistcoroutines.data.repository.ToDoRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appRepository = module {
    fun provideToDoRepository(context: Context, dao: ToDoDao): ToDoRepository {
        return ToDoRepositoryImpl(dao, context)
    }

    single { provideToDoRepository(androidContext(), get()) }
}

