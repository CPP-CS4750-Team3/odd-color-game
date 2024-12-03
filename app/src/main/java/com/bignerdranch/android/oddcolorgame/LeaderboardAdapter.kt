package com.bignerdranch.android.oddcolorgame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LeaderboardAdapter(private var scores: MutableList<Leaderboard>) : RecyclerView.Adapter<LeaderboardAdapter.ScoreViewHolder>() {

    inner class ScoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playerNameTextView: TextView = view.findViewById(R.id.playerName)
        val scoreTextView: TextView = view.findViewById(R.id.playerScore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_score, parent, false)
        return ScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val score = scores[position]
        holder.playerNameTextView.text = score.name
        holder.scoreTextView.text = score.score.toString()
    }

    override fun getItemCount() = scores.size

    fun updateScores(newScores: List<Leaderboard>) {
        //scores = newScores
        scores.clear()
        scores.addAll(newScores)
        notifyDataSetChanged()
    }
}
