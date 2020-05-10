package com.example.apilist.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailViewModel(
    val title: String,
    val url: String
) : ViewModel()

// Override ViewModelProvider.NewInstanceFactory to create the ViewModel (VM).
@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
    private val title: String,
    private val url: String
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = DetailViewModel(title, url) as T
}