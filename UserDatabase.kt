package com.example.roomdatabasetest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: UserDatabase?=null
        fun getInstance(context: Context): UserDatabase {
            if (instance==null) {
                instance = Room.databaseBuilder(context,UserDatabase::class.java,"UserDataBase")
                    .build()
            }
            return instance as UserDatabase
        }
    }
}