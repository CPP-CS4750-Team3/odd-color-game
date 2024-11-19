package com.bignerdranch.android.oddcolorgame.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bignerdranch.android.oddcolorgame.Leaderboard

@Dao
interface LeaderboardDao {
    @Query("SELECT * FROM leaderboard")
    fun getScores(): List<Leaderboard>

    @Insert
    fun insertScore(score: Leaderboard)
}