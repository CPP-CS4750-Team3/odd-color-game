package com.bignerdranch.android.oddcolorgame

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var leaderboardViewModel: LeaderboardViewModel
    private lateinit var leaderboardAdapter: LeaderboardAdapter

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
    }
}
