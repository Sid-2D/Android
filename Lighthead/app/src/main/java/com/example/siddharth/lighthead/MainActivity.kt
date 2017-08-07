package com.example.siddharth.lighthead

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myButtonListenerMethod()
    }

    fun myButtonListenerMethod() {
        val button = findViewById(R.id.button) as Button
        button.setOnClickListener {
            print("1")
            val bgElement = findViewById(R.id.activity_main) as ConstraintLayout
            print("2")
            val color = (bgElement.background as? ColorDrawable)?.color
            if (color== Color.RED) {
                bgElement.setBackgroundColor(Color.BLUE)
            } else {
                bgElement.setBackgroundColor(Color.RED)
            }
        }
    }
}
