package com.example.musictheory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_layout)

        var userDatabase  = Room.databaseBuilder(this,UserDatabase::class.java,"UserDatabase").build()

        var nameEntry = findViewById<TextView>(R.id.createUserNameEntry)
        var passwordEntry = findViewById<TextView>(R.id.createPasswordEntry)
        var createButton = findViewById<Button>(R.id.createUserButton)
        var cancelButton = findViewById<Button>(R.id.cancelButton)

        cancelButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        createButton.setOnClickListener{

            if(nameEntry.text.length  == 0)
            {
                Toast.makeText(this,"Please enter a username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(nameEntry.text.toString() == "Username")
            {
                Toast.makeText(this,"Invalid Username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(passwordEntry.text.length  == 0)
            {
                Toast.makeText(this,"Please enter a password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }



            var allUsers  = mutableListOf<UserObject>()

            var t =  Thread(Runnable {

                allUsers = userDatabase.userDao().ReadAllUsers()

            })
            t.start()

            while(t.isAlive) {}

            Log.e("egg", "r: " + allUsers.size.toString())

            for (u in allUsers)
                if (u.name == nameEntry.text.toString()) {
                    Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

            var newUser = UserObject()
            newUser.name = nameEntry.text.toString()
            newUser.password = passwordEntry.text.toString()


            var t2 =  Thread(Runnable { userDatabase.userDao().AddUser(newUser) })

            t2.start()

            while (t2.isAlive){}

            Toast.makeText(this, "User " + newUser.name + " Created", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

}
