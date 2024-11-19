package com.bignerdranch.android.oddcolorgame

import android.app.Application
import android.util.Log
import android.view.Display
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.bignerdranch.android.oddcolorgame.database.LeaderboardDao
import com.bignerdranch.android.oddcolorgame.database.LeaderboardDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.util.Date
import java.util.UUID

private const val DATABASE_NAME = "leaderboard-database.db"

private const val TAG = "LeaderboardActivity"

class LeaderboardViewModel(application: Application) : AndroidViewModel(application) {
    private val leaderboardDao: LeaderboardDao
    private val _scores = MutableLiveData<List<Leaderboard>>()
    val scores: LiveData<List<Leaderboard>> get() = _scores

    init {
        val db = Room.databaseBuilder(
            application,
            LeaderboardDatabase::class.java, "leaderboard-database"
        ).build()
        leaderboardDao = db.LeaderboardDao()
        populateTestData()
        loadScores()
//        scores = liveData {
//            val leaderboardList = leaderboardDao.getScores()
//            emit(leaderboardList)
//        }
    }

    private fun loadScores() {
        viewModelScope.launch(Dispatchers.IO) {
            val scoreList = leaderboardDao.getScores()
            withContext(Dispatchers.Main) {
                _scores.value = scoreList
            }
        }
    }

    private fun populateTestData() {
        viewModelScope.launch(Dispatchers.IO) {
            val sampleData = listOf(
                Leaderboard(UUID.randomUUID(), "Alice", 900, Date()),
                Leaderboard(UUID.randomUUID(), "Bob", 750, Date()),
                Leaderboard(UUID.randomUUID(), "Charlie", 800, Date()),
                Leaderboard(UUID.randomUUID(), "David", 670, Date()),
                Leaderboard(UUID.randomUUID(), "Eve", 950, Date())
            )
            sampleData.forEach {
                leaderboardDao.insertScore(it)
            }
        }
    }

}