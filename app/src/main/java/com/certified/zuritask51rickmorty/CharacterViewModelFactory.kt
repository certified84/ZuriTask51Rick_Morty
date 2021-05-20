package com.certified.zuritask51rickmorty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.certified.zuritask51rickmorty.api.ApiService

@Suppress("UNCHECKED_CAST")
class CharacterViewModelFactory(private val apiService: ApiService) :  ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java))
            return CharacterViewModel(apiService) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}