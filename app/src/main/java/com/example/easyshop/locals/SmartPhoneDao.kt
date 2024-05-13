package com.example.easyshop.locals

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SmartPhoneDao {
    @Query("SELECT * FROM smartphones")
     fun getAllSmartPhones(): List<SmartPhoneEntity>

    @Insert
     fun insertSmartPhones(smartPhones: List<SmartPhoneEntity>)

     @Insert
     fun insertSmartPhone(smartPhone: SmartPhoneEntity)
}