package com.bignerdranch.android.oddcolorgame

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get

class GameOverActivity : AppCompatActivity() {

    private lateinit var leaderboardViewModel: LeaderboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        leaderboardViewModel = ViewModelProvider(this)[LeaderboardViewModel::class.java]
        val finalScore = intent.getIntExtra("finalScore", 0)
        val difficulty = intent.getStringExtra("difficulty")?: "EASY"
        val scoreTextView = findViewById<TextView>(R.id.finalScoreTextView)
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val saveButton = findViewById<ImageButton>(R.id.saveButton)

        scoreTextView.text = "Score: $finalScore"

        saveButton.setOnClickListener {
            val playerName = nameEditText.text.toString()

            if (playerName.isNotBlank()) {
                leaderboardViewModel.addScore(playerName, finalScore, difficulty)
                Toast.makeText(this, "Score added to leaderboard!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Enter a name", Toast.LENGTH_SHORT).show()
            }
        }

//        val newGameButton = findViewById<Button>(R.id.newGame)
//        newGameButton.setOnClickListener {
//            finish()
//        }

        val goHomeButton = findViewById<ImageButton>(R.id.goHome)
        goHomeButton.setOnClickListener {
            finish()
        }
    }
}
