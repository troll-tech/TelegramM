package ru.borz7zy.telegramm.views.ui.theme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import ru.borz7zy.telegramm.R

object Icons{
    @Composable
    fun message_outline(): Painter = painterResource(id = R.drawable.message_outline)

    @Composable
    fun settings_outline(): Painter = painterResource(id = R.drawable.settings_outline)

    @Composable
    fun user_circle_fill_light(): Painter = painterResource(id = R.drawable.user_circle_fill)

    @Composable
    fun user_circle_fill_dark(): Painter = painterResource(id = R.drawable.user_circle_fill_dark)

    @Composable
    fun pin_outline(): Painter = painterResource(id = R.drawable.pin_outline)

    @Composable
    fun mute_outline(): Painter = painterResource(id = R.drawable.mute_outline)
}