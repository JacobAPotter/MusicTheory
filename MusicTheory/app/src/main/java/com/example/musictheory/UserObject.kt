package com.example.musictheory

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class UserObject
{
    @PrimaryKey
    var name = ""

    var password = ""

    var progress = "0000"

    fun ProgressInClass(classNum: Int): Int {

        return progress[classNum].toString().toInt()

    }

    fun SetProgress(classNum: Int, prog: Int)
    {
        var p = ""

        if(classNum >= progress.length)
        {
            Log.e("egg","classNum error: " + classNum.toString())
            return
        }

        for(i in 0..3)
        {
            if(i == classNum)
                p += prog.toString()
            else
                p += progress[i].toString()
        }

        progress = p
    }
}

