package com.example.youtube2.core.di

import com.example.youtube2.data.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single { Repository(get()) }
}