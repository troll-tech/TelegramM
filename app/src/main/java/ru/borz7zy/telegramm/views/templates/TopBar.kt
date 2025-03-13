package ru.borz7zy.telegramm.views.templates

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.materials.CupertinoMaterials
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import ru.borz7zy.telegramm.views.ui.theme.vkSansDisplayFamily

@OptIn(ExperimentalMaterial3Api::class, ExperimentalHazeMaterialsApi::class)
@Composable
fun TopBar(title: String, hazeState: HazeState){

    val isBlurSupported = remember { Build.VERSION.SDK_INT >= Build.VERSION_CODES.S }
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 25.dp,
                    bottomEnd = 25.dp
                )
            )
            .height(statusBarPadding + 60.dp)
            .then(
                if (isBlurSupported) {
                    Modifier.hazeEffect(
                        state = hazeState,
                        style = CupertinoMaterials.ultraThin()
                    )
                } else {
                    Modifier
                        .background(
                            Color.Black.copy(alpha = 0.5f)
                        )
                }
            )
    ){
        Text(
            text = title,
            fontFamily = vkSansDisplayFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        )
    }
}