package com.bignerdranch.android.oddcolorgame

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val finalScore = intent.getIntExtra("finalScore", 0)
        val scoreTextView = findViewById<TextView>(R.id.finalScoreTextView)
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val saveButton = findViewById<Button>(R.id.saveButton)

        scoreTextView.text = "Score: $finalScore"

        saveButton.setOnClickListener {
            val playerName = nameEditText.text.toString()
            //place code to save to leaderboard.
            finish()
        }
    }
}
