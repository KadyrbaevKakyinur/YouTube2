package com.example.youtube2.core.di

import com.example.youtube2.core.network.RemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { RemoteDataSource(get()) }
}