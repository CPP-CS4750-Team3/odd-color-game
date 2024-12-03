package com.bignerdranch.android.oddcolorgame

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.oddcolorgame.ColorUtils.generateRandomBaseColor
import com.bignerdranch.android.oddcolorgame.ColorUtils.generateTargetColor

class GameScreenActivity : AppCompatActivity(), SquareAdapter.SquareClickListener {
    private lateinit var squareAdapter: SquareAdapter
    private lateinit var gameDifficulty: Difficulty
    private var numOfSquares = 6
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val getDifficulty = intent.getStringExtra("DIFFICULTY") ?: "EASY"
        gameDifficulty = Difficulty.valueOf(getDifficulty)
        numOfSquares = gameDifficulty.rows * gameDifficulty.columns

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewGrid)
        recyclerView.layoutManager = GridLayoutManager(this, gameDifficulty.columns) // 4 columns in the grid

        findViewById<TextView>(R.id.game_difficulty).text = "Difficulty: ${gameDifficulty.name}"

        // Initialize adapter with initial data (e.g., 16 squares with one being different)
        val initialSquares = MutableList(numOfSquares) { false } // First square is different
        val differentSquareIndex = (0 until numOfSquares).random()
        initialSquares[differentSquareIndex] = true

        squareAdapter = SquareAdapter(initialSquares, gameDifficulty, score, this)
        recyclerView.adapter = squareAdapter

        updateScoreAndStage()
    }

    override fun onSquareClick(position: Int) {
        val clickedSquareIsDifferent = squareAdapter.squares[position]
        if (clickedSquareIsDifferent) { score++; updateScoreAndStage(); updateSquares() }
    }

    private fun updateSquares() {
        val newSquares = MutableList(numOfSquares) { false }
        val differentSquareIndex = (0 until numOfSquares).random()
        newSquares[differentSquareIndex] = true
        squareAdapter.updateSquares(newSquares, score)
    }

    private fun updateScoreAndStage() {
        findViewById<TextView>(R.id.game_score).text = "Score: $score"

        val stage = score / 6 + 1
        findViewById<TextView>(R.id.game_stage).text = "Stage: $stage"
    }
}