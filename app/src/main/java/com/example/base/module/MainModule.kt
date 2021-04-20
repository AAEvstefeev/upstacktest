package com.example.base.module

import com.example.base.MainViewModel
import com.example.base.NetworkClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var mainModule = module {
    viewModel {
        MainViewModel(get())
    }

    factory {
        NetworkClient()
    }
}