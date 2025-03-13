package ru.borz7zy.telegramm.views.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource


@Composable
fun ChatsScreen(innerPadding: PaddingValues, hazeState: HazeState){
    Box{
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(state = hazeState)
        ) {
            item {
                Spacer(modifier = Modifier
                    .height(innerPadding.calculateTopPadding())
                )
            }
            items(50) { index ->
                Text(
                    text = "Элемент $index",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            item {
                Spacer(modifier = Modifier
                    .height(innerPadding.calculateBottomPadding())
                )
            }
        }
    }
}