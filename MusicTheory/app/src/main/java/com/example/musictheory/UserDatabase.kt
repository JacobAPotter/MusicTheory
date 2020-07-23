package com.example.musictheory

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UserObject::class), version = 1)
abstract class UserDatabase() : RoomDatabase() {

    abstract fun userDao(): UserDao

}
