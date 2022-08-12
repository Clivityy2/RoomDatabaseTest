package com.example.roomdatabasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.roomdatabasetest.data.User
import com.example.roomdatabasetest.data.UserDatabase
import com.example.roomdatabasetest.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userDB: UserDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDB = UserDatabase.getInstance(this)

        binding.btnWriteData.setOnClickListener {
            writeData()
        }
    }

    private fun writeData() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()

        if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
            val user = User(
                null, firstName, lastName

            )
            GlobalScope.launch(Dispatchers.IO) {
                userDB.userDao().insertAll(user)
            }
            binding.etFirstName.text.clear()
            binding.etLastName.text.clear()


            Toast.makeText(this, "Data Written", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Could Not Write Data", Toast.LENGTH_SHORT).show()
        }
    }
}

