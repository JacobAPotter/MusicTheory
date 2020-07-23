package com.example.musictheory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var userDatabase  = Room.databaseBuilder(this,UserDatabase::class.java,"UserDatabase").build()

        //Thread(Runnable { userDatabase.clearAllTables() } ).start()


        var userNameEntry = findViewById<TextView>(R.id.userNameEntry)
        var passwordEntry = findViewById<TextView>(R.id.passwordEntry)

        var registrationButton = findViewById<Button>(R.id.registerButton)
        registrationButton.setOnClickListener{

            val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
            startActivity(intent)
        }

        var loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {

            var allUsers = mutableListOf<UserObject>()

            var t = Thread(Runnable {
                 allUsers = userDatabase.userDao().ReadAllUsers()
            }
            )

            t.start()

            while(t.isAlive) {}


            if(userNameEntry.text.isEmpty() || userNameEntry.text.toString() == "Username")
            {
                Toast.makeText(this, "Please enter a Username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var userNameFound = false

            for (a in allUsers)
            {
                if (a.name == userNameEntry.text.toString()) {
                    userNameFound = true
                    if (a.password == passwordEntry.text.toString()) {
                        //log in successful

                        val intent = Intent(this@MainActivity, ClassesActivity::class.java)

                        intent.putExtra("user", a.name)
                        startActivity(intent)
                        return@setOnClickListener
                    } else {
                        Toast.makeText(this, "incorrect password", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                }
            }

            if(!userNameFound)
            {
                Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show()
            }

        }

    }
}
