package com.bignerdranch.android.oddcolorgame

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var leaderboardViewModel: LeaderboardViewModel
    private lateinit var leaderboardAdapter: LeaderboardAdapter
    private var difficulty = "EASY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLeaderboard)
        recyclerView.layoutManager = LinearLayoutManager(this)

        leaderboardAdapter = LeaderboardAdapter(mutableListOf())
        recyclerView.adapter = leaderboardAdapter

        leaderboardViewModel = ViewModelProvider(this)[LeaderboardViewModel::class.java]

        leaderboardViewModel.scores.observe(this) { scores ->
            leaderboardAdapter.updateScores(scores)
            //leaderboardAdapter = LeaderboardAdapter(scores)
            //recyclerView.adapter = leaderboardAdapter
        }

        val difficultyButton = findViewById<Button>(R.id.Difficulty)
        var title = findViewById<TextView>(R.id.leaderboardTitle)
        difficultyButton.setOnClickListener {
            val popupMenu = PopupMenu(this, difficultyButton)

            popupMenu.menuInflater.inflate(R.menu.difficulty_popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                findViewById<TextView>(R.id.leaderboardTitle).text = "Leaderboard - " + menuItem.title.toString()
                loadScoresForLevel(menuItem.title.toString())
                true
            }

            popupMenu.show()
        }

        val goHomeButton = findViewById<Button>(R.id.goHome)
        goHomeButton.setOnClickListener {
            finish()
        }

        loadScoresForLevel("EASY")
    }
    private fun loadScoresForLevel(difficulty: String) {
        leaderboardViewModel.loadScores(difficulty).observe(this) { scores ->
            leaderboardAdapter.updateScores(scores)
        }
    }
}
