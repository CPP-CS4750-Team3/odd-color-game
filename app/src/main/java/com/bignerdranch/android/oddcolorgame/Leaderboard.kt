package com.bignerdranch.android.oddcolorgame
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "leaderboard")
data class Leaderboard(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val name: String,
    val score: Int,
    val date: Date,
    val difficulty: String
)