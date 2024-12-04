package com.bignerdranch.android.oddcolorgame.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bignerdranch.android.oddcolorgame.Leaderboard

@Database(entities = [Leaderboard::class], version = 2, exportSchema = false)
@TypeConverters(LeaderboardTypeConverters::class)
abstract class LeaderboardDatabase : RoomDatabase() {
    abstract fun LeaderboardDao(): LeaderboardDao

    companion object {
        @Volatile
        private var INSTANCE: LeaderboardDatabase? = null

        fun getDatabase(context: Context): LeaderboardDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LeaderboardDatabase::class.java,
                    "leaderboard-database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE leaderboard ADD COLUMN difficulty TEXT NOT NULL DEFAULT 'EASY'")
            }
        }
    }
}