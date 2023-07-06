package com.example.todolistcoroutines.di

import com.example.todolistcoroutines.viewmodel.mainActivity.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainActivityViewModel(toDoRepository = get()) }

}

