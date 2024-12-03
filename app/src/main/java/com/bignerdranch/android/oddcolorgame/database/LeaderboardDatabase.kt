package com.bignerdranch.android.oddcolorgame.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bignerdranch.android.oddcolorgame.Leaderboard

@Database(entities = [Leaderboard::class], version = 1)
@TypeConverters(LeaderboardTypeConverters::class)
abstract class LeaderboardDatabase : RoomDatabase() {
    abstract fun LeaderboardDao(): LeaderboardDao
}