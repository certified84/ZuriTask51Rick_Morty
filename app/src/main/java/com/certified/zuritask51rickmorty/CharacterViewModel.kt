package com.certified.zuritask51rickmorty

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.certified.zuritask51rickmorty.api.Api
import com.certified.zuritask51rickmorty.api.ApiResponse
import com.certified.zuritask51rickmorty.api.ApiService
import kotlinx.coroutines.launch

class CharacterViewModel(private val apiService: ApiService) : ViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>>
        get() = _characters

    private var _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar: LiveData<Boolean>
        get() = _showProgressBar

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            try {
                _characters.value = apiService.getCharacters().results
                _showProgressBar.value = false
            } catch (e: Exception) {
                Log.e("ViewModel", "getCharacters: An error occurred. ${e.message}")
            }
        }
    }
}