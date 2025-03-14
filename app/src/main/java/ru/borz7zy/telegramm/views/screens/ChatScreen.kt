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
import org.json.JSONObject
import ru.borz7zy.telegramm.views.templates.chats.ChatBox


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
//            items(50) { index ->
//                Text(
//                    text = "Элемент $index",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp)
//                )
//            }
            val chatList = arrayListOf(
                JSONObject().apply {
                    put("avatar", "0")
                    put("chatName", "Preview")
                    put("chatStatus", "Test message")
                    put("chatPinned", true)
                    put("chatMuted", false)
                    put("chatUnreaded", 10)
                    put("timeLastActive", "14:48")
                },
                JSONObject().apply {
                    put("avatar", "0")
                    put("chatName", "Preview3")
                    put("chatStatus", "Preview3 is typing...")
                    put("chatPinned", false)
                    put("chatMuted", true)
                    put("chatUnreaded", 10488)
                    put("timeLastActive", "14:32")
                }
            )
            item{
                ChatBox(categoryName = "Личные сообщения", content = chatList)
            }
            val chatList2 = arrayListOf(
                JSONObject().apply {
                    put("avatar", "0")
                    put("chatName", "Preview4")
                    put("chatStatus", "Noooo")
                    put("chatPinned", true)
                    put("chatMuted", true)
                    put("chatUnreaded", 7)
                    put("timeLastActive", "13:12")
                },
                JSONObject().apply {
                    put("avatar", "0")
                    put("chatName", "Preview2")
                    put("chatStatus", "userName is typing...")
                    put("chatPinned", false)
                    put("chatMuted", true)
                    put("chatUnreaded", 2025)
                    put("timeLastActive", "06:11")
                }
            )
            item{
                ChatBox(categoryName = "Группы", content = chatList2)
            }
            val chatList3 = arrayListOf(
                JSONObject().apply {
                    put("avatar", "0")
                    put("chatName", "Preview5")
                    put("chatStatus", "Очередной щит")
                    put("chatPinned", false)
                    put("chatMuted", true)
                    put("chatUnreaded", 1)
                    put("timeLastActive", "13:12")
                },
                JSONObject().apply {
                    put("avatar", "0")
                    put("chatName", "Preview6")
                    put("chatStatus", "Какой то щит")
                    put("chatPinned", false)
                    put("chatMuted", true)
                    put("chatUnreaded", 4)
                    put("timeLastActive", "06:11")
                },
                JSONObject().apply {
                    put("avatar", "0")
                    put("chatName", "Preview7")
                    put("chatStatus", "Sheeeeeet")
                    put("chatPinned", false)
                    put("chatMuted", true)
                    put("chatUnreaded", 8)
                    put("timeLastActive", "06:11")
                },JSONObject().apply {
                    put("avatar", "0")
                    put("chatName", "Preview8")
                    put("chatStatus", "Очередной мамкин щитпостер что то запостил, ничего необычного")
                    put("chatPinned", false)
                    put("chatMuted", true)
                    put("chatUnreaded", 8)
                    put("timeLastActive", "06:11")
                }
            )
            item{
                ChatBox(categoryName = "Каналы", content = chatList3)
            }
            item {
                Spacer(modifier = Modifier
                    .height(innerPadding.calculateBottomPadding())
                )
            }
        }
    }
}