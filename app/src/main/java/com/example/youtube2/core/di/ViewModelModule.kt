package com.example.youtube2.core.di

import com.example.youtube2.ui.detail.DetailViewModel
import com.example.youtube2.ui.playlist.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}