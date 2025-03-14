package ru.borz7zy.telegramm.views.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

@Composable
fun NoiseBackgroundBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFEAEAEA))
            .drawBehind {
                for (i in 0 until 500) {
                    val x = Random.nextFloat() * size.width
                    val y = Random.nextFloat() * size.height
                    drawCircle(
                        color = Color.Black.copy(alpha = 0.1f),
                        radius = 1f,
                        center = Offset(x, y)
                    )
                }
            }
    )
}
