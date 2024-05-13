package com.example.easyshop.locals

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
     fun getUserByUsernameAndPassword(email: String, password: String): User?
}