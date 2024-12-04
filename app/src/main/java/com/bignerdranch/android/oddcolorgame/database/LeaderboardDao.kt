package com.bignerdranch.android.oddcolorgame.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bignerdranch.android.oddcolorgame.Difficulty
import com.bignerdranch.android.oddcolorgame.Leaderboard

@Dao
interface LeaderboardDao {
    @Query("SELECT * FROM leaderboard Where difficulty = :difficulty ORDER BY score DESC")
    fun getScores(difficulty: String): List<Leaderboard>

    @Insert
    fun insertScore(score: Leaderboard)
}