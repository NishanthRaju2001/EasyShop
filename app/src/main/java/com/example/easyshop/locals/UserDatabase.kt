package com.example.easyshop.locals

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao():UserDao


    companion object{
        private var appDatabaseInstance:UserDatabase?=null

        fun getInstance(context: Context):UserDatabase{
             appDatabaseInstance= Room.databaseBuilder(
                context,
                UserDatabase::class.java,
                "user_db"
            ).allowMainThreadQueries()
                .build()
            return appDatabaseInstance!!
        }
    }
}