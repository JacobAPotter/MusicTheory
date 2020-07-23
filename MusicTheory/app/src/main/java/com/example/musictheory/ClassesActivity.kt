package com.example.musictheory

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_classes.*

class ClassesActivity : AppCompatActivity() {

    var lessonList = LessonList()

    var currentUser = UserObject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classes)

        var userDatabase : UserDatabase = Room.databaseBuilder(this,UserDatabase::class.java,"UserDatabase").build()

        var userName = ""
        var lessonNum = -1
        var progress = -1

        var bundel = intent.extras

        if (bundel?.get("user") != null)
            userName = bundel.get("user") as String

        if (bundel?.get("lesson") != null)
            lessonNum = bundel.get("lesson") as Int

        if (bundel?.get("prog") != null)
            progress = bundel.get("prog") as Int


        var t = Thread(Runnable {
            var allUsers = userDatabase.userDao().ReadAllUsers()

            for(u in allUsers)
            {
                if(u.name == userName)
                {
                    currentUser = u
                    if (progress >= 0 && lessonNum >= 0)
                        currentUser.SetProgress(lessonNum,  progress)
                    userDatabase.userDao().UpdateUser(currentUser)
                }
            }
        })

            t.start()
           while(t.isAlive){}
        Toast.makeText(this, "Logged in as " + userName, Toast.LENGTH_SHORT).show()
        classesPageRecyclerView.layoutManager = LinearLayoutManager(this)
        classesPageRecyclerView.adapter = LessonAdapter()
    }


    inner class LessonAdapter: RecyclerView.Adapter<LessonHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonHolder {

            val inflater = LayoutInflater.from(this@ClassesActivity)
            val itemView = inflater.inflate(R.layout.single_class_display, parent, false)

            return LessonHolder(itemView)
        }

        override fun getItemCount(): Int = lessonList.lessonList.size

        override fun onBindViewHolder(holder: LessonHolder, position: Int) {
            holder.bindStudent(lessonList.lessonList[position])
        }
    }

    inner class LessonHolder constructor(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        private val nameView: TextView = itemView.findViewById((R.id.classDisplayName))
        private val buttonView : Button = itemView.findViewById(R.id.selectClassButton)
        private  val progressView : TextView = itemView.findViewById(R.id.classDisplayProgress)

        fun bindStudent(lesson: LessonObject) {
            nameView.text = lesson.name

            when(lesson.drawable)
            {
                "harmony"->buttonView.setBackgroundResource(R.drawable.hramony)
                "circle"->buttonView.setBackgroundResource(R.drawable.circle)
                "rhythm"->buttonView.setBackgroundResource(R.drawable.rhythm)
                "modes"->buttonView.setBackgroundResource(R.drawable.modes)
            }

            progressView.text = "Progress: " + (currentUser.ProgressInClass(lesson.index) * 25).toString() + "%"


            buttonView.setOnClickListener{

                var intent = Intent(this@ClassesActivity,SlideActivity::class.java)
                intent.putExtra("classIndex", lesson.index)
                intent.putExtra("user",currentUser.name)
                startActivity(intent)
            }

            }

        }
    }


