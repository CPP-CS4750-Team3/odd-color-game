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
        loadScores()
    }

    private fun loadScores() {
        viewModelScope.launch(Dispatchers.IO) {
            val scoreList = leaderboardDao.getScores()
            withContext(Dispatchers.Main) {
                _scores.value = scoreList
            }
        }
    }

    fun addScore(name: String, score: Int) {
        val scoreInsert = Leaderboard(UUID.randomUUID(), name, score, Date())
        viewModelScope.launch(Dispatchers.IO) {
            leaderboardDao.insertScore(scoreInsert)
        }
    }
}