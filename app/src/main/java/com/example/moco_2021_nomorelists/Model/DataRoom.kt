package com.example.moco_2021_nomorelists.Model

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.CoroutineScope

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "street") val street: String?,
    @ColumnInfo(name = "city") val city: String?,
    @ColumnInfo(name = "zip") val zip: Int?,
    @ColumnInfo(name = "phone_number") val phoneNumber: Int?,
    @ColumnInfo(name = "email") val email: String?
    )

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id IS (:userId)")
    fun getById(userId: Int): User

    @Insert
    suspend fun insert(vararg user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
public abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): UserRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }


}