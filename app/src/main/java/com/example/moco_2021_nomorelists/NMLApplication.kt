package com.example.moco_2021_nomorelists

import android.app.Application
import com.example.moco_2021_nomorelists.Model.UserRepository
import com.example.moco_2021_nomorelists.Model.UserRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NMLApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { UserRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { UserRepository(database.userDao()) }
}