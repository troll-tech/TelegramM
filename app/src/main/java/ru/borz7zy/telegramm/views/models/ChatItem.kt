package ru.borz7zy.telegramm.views.models

data class ChatItem(val avatar: String?,
                    val chatName: String,
                    val chatStatus: String,
                    val chatPinned: Boolean,
                    val chatMuted: Boolean,
                    val chatUnreaded: Int,
                    val timeLastActive: String)
