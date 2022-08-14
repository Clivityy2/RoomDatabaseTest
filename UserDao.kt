package com.example.roomdatabasetest.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
        @Insert
        fun insertAll(vararg users: User)

        @Query("SELECT * FROM user_table WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1")
        fun findByName(first: String, last: String): User

        @Query("DELETE FROM user_table")
        fun deleteAllUsers()

        @Delete
        fun deleteUser(user: User)
}