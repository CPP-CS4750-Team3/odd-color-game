package com.bignerdranch.android.oddcolorgame

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SquareAdapter(var squares: List<Boolean>, private var difficulty: Difficulty, score: Int, private val listener: SquareClickListener) : RecyclerView.Adapter<SquareAdapter.SquareViewHolder>() {
    private var baseColor = ColorUtils.generateRandomBaseColor()
    private var targetDiff = initializeColorsRange(score)
    private var differentColor = ColorUtils.generateTargetColor(baseColor, targetDiff)

    private fun initializeColorsRange(score: Int): Float {
        return (0.5f - (score / 10) * 0.05f).coerceAtLeast(0.05f) //change min to .1f or higher?, lower 10 for faster progression?
    }

    interface SquareClickListener {
        fun onSquareClick(position: Int)
    }

    inner class SquareViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val squareView: View = view.findViewById(R.id.squareView)
        init {squareView.setOnClickListener{listener.onSquareClick(adapterPosition)}}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquareViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_square, parent, false)
        return SquareViewHolder(view)
    }

    override fun onBindViewHolder(holder: SquareViewHolder, position: Int) {
        val isDifferentSquare = squares[position]

        if (isDifferentSquare) {
            holder.squareView.setBackgroundColor(differentColor)
        } else {
            holder.squareView.setBackgroundColor(baseColor)
        }
    }

    override fun getItemCount() = squares.size
    fun updateSquares(newSquares: List<Boolean>, score:Int) {
        baseColor = ColorUtils.generateRandomBaseColor()
        targetDiff = initializeColorsRange(score)
        differentColor = ColorUtils.generateTargetColor(baseColor, targetDiff)
        squares = newSquares
        notifyDataSetChanged()
    }
}
