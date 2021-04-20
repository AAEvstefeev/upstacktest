package com.example.base

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val mainModule = module {


    viewModel {
        MainViewModel(get())
    }

    factory {
        NetworkClient()
    }
}