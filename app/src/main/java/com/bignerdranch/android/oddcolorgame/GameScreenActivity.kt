package com.bignerdranch.android.oddcolorgame

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.oddcolorgame.ColorUtils.generateRandomBaseColor
import com.bignerdranch.android.oddcolorgame.ColorUtils.generateTargetColor

class GameScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)
    }



    //set colors of buttons.  float value likely to be changed
    fun setButtonColors() {
        val baseColor = generateRandomBaseColor()
        val oddColor = generateTargetColor(baseColor, 0.5f)

    }


}