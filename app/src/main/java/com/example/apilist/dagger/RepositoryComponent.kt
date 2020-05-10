package com.example.apilist.dagger

import com.example.apilist.ui.viewmodels.MainViewModel
import dagger.Component

@Component(modules = [RepositoryModule::class])
interface RepositoryComponent {
    fun inject(app: MainViewModel)
}