package ru.borz7zy.telegramm.views.templates.chats

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import ru.borz7zy.telegramm.views.models.ChatItem
import ru.borz7zy.telegramm.views.ui.theme.Icons.mute_outline
import ru.borz7zy.telegramm.views.ui.theme.Icons.pin_outline
import ru.borz7zy.telegramm.views.ui.theme.Icons.user_circle_fill_light
import ru.borz7zy.telegramm.views.ui.theme.Red99
import ru.borz7zy.telegramm.views.ui.theme.Gray49
import ru.borz7zy.telegramm.views.ui.theme.GrayAA
import ru.borz7zy.telegramm.views.ui.theme.Icons.user_circle_fill_dark
import ru.borz7zy.telegramm.views.ui.theme.vkSansDisplayFamily

@Composable
fun ChatElement(chatItem: ChatItem) {
    val darkMode = isSystemInDarkTheme()
    val avatarPainter: Painter = when (chatItem.avatar) {
        "0" -> if (darkMode) user_circle_fill_dark() else user_circle_fill_light()
        null -> if (darkMode) user_circle_fill_dark() else user_circle_fill_light()
        else -> rememberAsyncImagePainter(chatItem.avatar)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = avatarPainter,
            contentDescription = "Avatar",
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .size(36.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = chatItem.chatName,
                    color = if (darkMode) Color.White else Color.Black,
                    fontFamily = vkSansDisplayFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
                if (chatItem.chatMuted) {
                    Image(
                        painter = mute_outline(),
                        contentDescription = "Muted",
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .size(18.dp),
                        colorFilter = ColorFilter.tint(if (darkMode) GrayAA else Gray49)
                    )
                }
            }
            Text(
                text = chatItem.chatStatus,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = if (darkMode) GrayAA else Gray49,
                fontFamily = vkSansDisplayFamily,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(top = 2.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = chatItem.timeLastActive,
                color = if (darkMode) GrayAA else Gray49,
                fontFamily = vkSansDisplayFamily,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 2.dp)) {
                if (chatItem.chatUnreaded > 0) {
                    Box(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .background(
                                if (chatItem.chatMuted) {
                                    if(darkMode){
                                        GrayAA
                                    }else{
                                        Gray49
                                    }
                                } else {
                                    Red99
                                }, CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = chatItem.chatUnreaded.toString(),
                            modifier = Modifier
                                .padding(start = 5.dp, end = 5.dp),
                            color = Color.White,
                            fontFamily = vkSansDisplayFamily,
                            fontSize = 14.sp,
                        )
                    }
                }
                if (chatItem.chatPinned) {
                    Image(
                        painter = pin_outline(),
                        contentDescription = "Pinned",
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .size(18.dp),
                        colorFilter = ColorFilter.tint(if (darkMode) GrayAA else Gray49)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    ChatElement(
        chatItem = ChatItem(
            avatar = null,
            chatName = "Preview",
            chatStatus = "Hello, World!",
            chatPinned = false,
            chatMuted = false,
            chatUnreaded = 10,
            timeLastActive = "14:48"
        )
    )
}

@Preview
@Composable
fun PreviewTwo() {
    ChatElement(
        chatItem = ChatItem(
            avatar = null,
            chatName = "Preview2",
            chatStatus = "userName is typing...",
            chatPinned = true,
            chatMuted = true,
            chatUnreaded = 10000,
            timeLastActive = "14:32"
        )
    )
}
