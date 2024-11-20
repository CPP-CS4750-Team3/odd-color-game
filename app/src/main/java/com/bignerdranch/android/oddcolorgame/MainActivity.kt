package com.bignerdranch.android.oddcolorgame

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Make background animated
        val titleScreenBg = findViewById<ConstraintLayout>(R.id.main_layout)
        val bgAnimation: AnimationDrawable = titleScreenBg.background as AnimationDrawable
        bgAnimation.setEnterFadeDuration(1000)
        bgAnimation.setExitFadeDuration(1000)
        bgAnimation.start()

        //Start Screen -> Game
        val startGameButton = findViewById<ImageButton>(R.id.play_button)
        startGameButton.setOnClickListener {
            val startToGame = Intent(this, GameScreen::class.java)
            startActivity(startToGame)
        }
    }
}