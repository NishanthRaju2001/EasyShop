package com.example.easyshop.locals

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SmartPhoneEntity::class], version = 1)
abstract class SmartPhoneDatabase : RoomDatabase() {
    abstract fun smartPhoneDao(): SmartPhoneDao

    companion object {

        private var INSTANCE: SmartPhoneDatabase? = null

        fun getDatabase(context: Context): SmartPhoneDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SmartPhoneDatabase::class.java,
                    "smartphone_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
