package com.example.moco_2021_nomorelists.ViewModel

import androidx.lifecycle.*
import com.example.moco_2021_nomorelists.Model.User
import com.example.moco_2021_nomorelists.Model.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val allUsers: LiveData<List<User>> = repository.allUsers.asLiveData()

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }
}

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}