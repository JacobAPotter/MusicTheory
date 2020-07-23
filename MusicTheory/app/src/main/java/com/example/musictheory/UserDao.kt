package com.example.musictheory

import androidx.room.*


@Dao
interface UserDao {

    @Insert
    fun AddUser(list : UserObject )

    @Query("select * from  users")
    fun ReadAllUsers() : MutableList<UserObject>

    @Delete
    fun RemoveUser(list : UserObject)

    @Update
    fun UpdateUser(List: UserObject)
}

