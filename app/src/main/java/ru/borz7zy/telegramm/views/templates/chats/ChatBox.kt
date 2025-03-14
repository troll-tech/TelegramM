package ru.borz7zy.telegramm.views.templates.chats

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.res.Configuration
import androidx.compose.ui.draw.shadow
import org.json.JSONObject
import ru.borz7zy.telegramm.views.models.ChatItem
import ru.borz7zy.telegramm.views.ui.theme.Gray49
import ru.borz7zy.telegramm.views.ui.theme.GrayAA
import ru.borz7zy.telegramm.views.ui.theme.vkSansDisplayFamily

@Composable
fun ChatBox(categoryName: String, content: ArrayList<JSONObject>){

    val darkMode = isSystemInDarkTheme()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 7.dp, vertical = 3.dp),
        verticalArrangement = Arrangement.Top
    ){
        Text(text = categoryName,
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 2.dp),
            color = if(darkMode) Gray49 else GrayAA,
            fontFamily = vkSansDisplayFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape = RoundedCornerShape(
                    topStart = 25.dp,
                    topEnd = 25.dp,
                    bottomStart = 25.dp,
                    bottomEnd = 25.dp
                ),
                spotColor = if(darkMode) Color.White else Color.Black
            )
            .background(if(darkMode) Gray49 else GrayAA,
                shape = RoundedCornerShape(
                    topStart = 25.dp,
                    topEnd = 25.dp,
                    bottomStart = 25.dp,
                    bottomEnd = 25.dp
                )
            )
            .clip(
                RoundedCornerShape(
                    topStart = 25.dp,
                    topEnd = 25.dp,
                    bottomStart = 25.dp,
                    bottomEnd = 25.dp
                )
            )
        ){
            Column(modifier = Modifier
                .padding(horizontal = 7.dp, vertical = 10.dp),
                    verticalArrangement = Arrangement.Top
            ){
                val content = content.mapNotNull { json->
                    try{
                        ChatItem(
                            avatar = json.optString("avatar", null)
                                .takeIf { it.isNotEmpty() && it != "0"},
                            chatName = json.getString("chatName"),
                            chatStatus = json.getString("chatStatus"),
                            chatPinned = json.getBoolean("chatPinned"),
                            chatMuted = json.getBoolean("chatMuted"),
                            chatUnreaded = json.getInt("chatUnreaded"),
                            timeLastActive = json.getString("timeLastActive")
                        )
                    } catch(e: Exception){
                        Log.e("ChatBox", "Невозможно пропарсить информацию о чатах!")
                        null
                    }
                }
                content.forEachIndexed { index, chat ->
                    ChatElement(chatItem = chat)
                    if (index != content.lastIndex) {
                        Spacer(
                            modifier = Modifier
                                .height(1.dp)
                                .padding(horizontal = 50.dp)
                                .fillMaxWidth()
                                .background(Color.Black)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewBox(){
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
    ChatBox(categoryName = "Личные сообщения", content = chatList)
}
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable()
fun PreviewTwoBox(){
    val chatList = arrayListOf(
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
    ChatBox(categoryName = "Группы", content = chatList)
}