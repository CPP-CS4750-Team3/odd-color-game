package com.bignerdranch.android.oddcolorgame

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {

    lateinit var difficulty: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Make background animated
        val titleScreenBg = findViewById<ConstraintLayout>(R.id.main_layout)
        val bgAnimation: AnimationDrawable = titleScreenBg.background as AnimationDrawable
        bgAnimation.setEnterFadeDuration(1000)
        bgAnimation.setExitFadeDuration(1000)
        bgAnimation.start()

        //options button as difficulty button
        //uses popup menu to choose difficulty between EASY, MEDIUM, and HARD
        val difficultyButton = findViewById<ImageButton>(R.id.options_button)
        difficultyButton.setOnClickListener {
            val popupMenu = PopupMenu(this@MainActivity, difficultyButton)

            popupMenu.menuInflater.inflate(R.menu.difficulty_popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                difficulty = menuItem.title.toString()
                Toast.makeText(this@MainActivity, "Difficully Chosen: "
                                + menuItem.title, Toast.LENGTH_SHORT).show()
                true
            }

            popupMenu.show()
        }

        //in case user presses play without selecting the difficulty, default EASY.
        difficulty = "EASY"

        //Start Screen -> Game
        val startGameButton = findViewById<ImageButton>(R.id.play_button)
        startGameButton.setOnClickListener {
            val startToGame = Intent(this, GameScreenActivity::class.java)
            startToGame.putExtra("DIFFICULTY",difficulty)
            startActivity(startToGame)
        }

        val leaderBoardButton = findViewById<ImageButton>(R.id.lb_button)
        leaderBoardButton.setOnClickListener{
            val intent = Intent(this, LeaderboardActivity::class.java)
            startActivity(intent)
        }
    }
}
