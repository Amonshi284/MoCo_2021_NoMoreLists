package com.example.moco_2021_nomorelists.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.moco_2021_nomorelists.Model.User
import com.example.moco_2021_nomorelists.Model.UserRepository
import kotlinx.coroutines.launch

class InputViewModel(private val repository: UserRepository) : ViewModel() {

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }
}

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InputViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InputViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}