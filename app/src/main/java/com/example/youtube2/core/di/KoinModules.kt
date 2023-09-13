package com.example.youtube2.core.di

import com.example.youtube2.core.network.networkModule

val koinModule = listOf(
    repositoryModule,
    remoteDataSourceModule,
    networkModule,
    viewModelModule
)