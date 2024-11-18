package com.bignerdranch.android.oddcolorgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Start Screen -> Game
        val startGameButton = findViewById<Button>(R.id.startGame)
        startGameButton.setOnClickListener {
            val startToGame = Intent(this, TestGameScreen::class.java)
            startActivity(startToGame)
        }
    }
}