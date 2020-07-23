package com.example.musictheory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

class SlideActivity : AppCompatActivity() {

    var lessonList = LessonList()
    var classIndex = 0
    var slideIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.slide_layout)

        classIndex = intent.extras?.get("classIndex") as Int
        var userName = intent.extras?.get("user") as String

        var text = findViewById<TextView>(R.id.slideText)

        text.text = lessonList.lessonList[classIndex].slides[0].text

        var nextButton = findViewById<Button>(R.id.nextButton)

        nextButton.setOnClickListener{

            slideIndex += 1

            if(slideIndex >= lessonList.lessonList[classIndex].slides.size)
            {
                var intent = Intent(this@SlideActivity, ClassesActivity::class.java)
                intent.putExtra("lesson", classIndex)
                intent.putExtra("prog",slideIndex)
                intent.putExtra("user",userName)
                startActivity(intent)
                return@setOnClickListener
            }

            text.text = lessonList.lessonList[classIndex].slides[slideIndex].text

        }

        var menuButton = findViewById<Button>(R.id.toMenuButton)

        menuButton.setOnClickListener{
            var intent = Intent(this@SlideActivity, ClassesActivity::class.java)
            intent.putExtra("lesson", classIndex)
            intent.putExtra("prog",slideIndex)
            intent.putExtra("user",userName)
            startActivity(intent)
            return@setOnClickListener
        }

        var slideImage = findViewById<ImageView>(R.id.slideImage)

        when(classIndex)
        {
            0->slideImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.rhythm, null))
            1->slideImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.hramony, null))
            2->slideImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.modes, null))
            3->slideImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.circle, null))
        }



    }
}
