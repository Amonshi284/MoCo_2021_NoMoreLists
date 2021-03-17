package com.example.moco_2021_nomorelists.Model

import androidx.annotation.WorkerThread

class UserRepository(private val userDao: UserDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }
}