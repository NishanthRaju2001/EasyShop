package com.example.easyshop.locals

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val fullName: String,
    val mobileNumber: String,
    val email: String,
    val password: String
)
