package com.bignerdranch.android.oddcolorgame

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "LeaderboardActivity"

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var leaderboardViewModel: LeaderboardViewModel
    private lateinit var leaderboardAdapter: LeaderboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"Logging activity leaderboard created")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLeaderboard)
        recyclerView.layoutManager = LinearLayoutManager(this)

        leaderboardAdapter = LeaderboardAdapter(mutableListOf())
        recyclerView.adapter = leaderboardAdapter

        leaderboardViewModel = ViewModelProvider(this).get(LeaderboardViewModel::class.java)

        leaderboardViewModel.scores.observe(this) { scores ->
            Log.d(TAG, "Scores observed: ${scores.size} items")
            leaderboardAdapter.updateScores(scores)
            //leaderboardAdapter = LeaderboardAdapter(scores)
            //recyclerView.adapter = leaderboardAdapter
        }
    }
}
