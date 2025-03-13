package ru.borz7zy.telegramm.views.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.borz7zy.telegramm.R

val vkSansDisplayFamily = FontFamily(
    Font(R.font.vk_sans_display_regular),
    Font(R.font.vk_sans_display_medium),
    Font(R.font.vk_sans_display_demibold),
    Font(R.font.vk_sans_display_bold)
)

val vkSansTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = vkSansDisplayFamily,
        fontWeight = FontWeight.Bold
    ),
    titleMedium = TextStyle(
        fontFamily = vkSansDisplayFamily,
        fontWeight = FontWeight.SemiBold
    ),
    titleSmall = TextStyle(
        fontFamily = vkSansDisplayFamily,
        fontWeight = FontWeight.Medium
    )
)

// Set of Material typography styles to start with
//val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
//    /* Other default text styles to override
//    titleLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 22.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.sp
//    ),
//    labelSmall = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Medium,
//        fontSize = 11.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    )
//    */
//)