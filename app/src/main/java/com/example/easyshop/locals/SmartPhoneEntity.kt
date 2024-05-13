package com.example.easyshop.locals

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "smartphones")
data class SmartPhoneEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val smartPhoneIV: Int,
    val smartPhoneTitle: String?,
    val smartPhoneDescription: String?,
    var price: Int
)