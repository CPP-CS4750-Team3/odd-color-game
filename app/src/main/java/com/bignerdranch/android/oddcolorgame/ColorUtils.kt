package com.bignerdranch.android.oddcolorgame

import android.graphics.Color
import kotlin.random.Random

object ColorUtils {

    fun generateRandomBaseColor(): Int {
        val hue = Random.nextFloat() * 360
        val saturation = 0.5f + Random.nextFloat() * 0.5f
        val lightness = 0.5f + Random.nextFloat() * 0.5f
        return Color.HSVToColor(floatArrayOf(hue, saturation, lightness))
    }

    fun generateTargetColor(baseColor: Int, colorDifference: Float): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(baseColor, hsv)
        hsv[2] = (hsv[2] * (1 - colorDifference)).coerceIn(0f, 1f)
        return Color.HSVToColor(hsv)
    }
}
