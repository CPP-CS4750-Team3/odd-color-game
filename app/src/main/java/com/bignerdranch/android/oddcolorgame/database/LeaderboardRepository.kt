package com.bignerdranch.android.oddcolorgame.database

import android.content.Context
import androidx.room.Room
import com.bignerdranch.android.oddcolorgame.Leaderboard

private const val DATABASE_NAME = "leaderboard-database"

class LeaderboardRepository private constructor(context: Context) {

    private val database: LeaderboardDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            LeaderboardDatabase::class.java,
            DATABASE_NAME
        )
        .createFromAsset(DATABASE_NAME)
        .build()

    suspend fun getScores(): List<Leaderboard> = database.LeaderboardDao().getScores()

    companion object {
        private var INSTANCE: LeaderboardRepository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = LeaderboardRepository(context)
            }
        }

        fun get(): LeaderboardRepository {
            return INSTANCE ?:
            throw IllegalStateException("LeaderboardRepository must be initialized")
        }
    }
}