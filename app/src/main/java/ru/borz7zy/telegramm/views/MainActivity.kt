package ru.borz7zy.telegramm.views

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.materials.CupertinoMaterials
import ru.borz7zy.telegramm.views.screens.ChatsScreen
import ru.borz7zy.telegramm.views.screens.SettingsScreen
import ru.borz7zy.telegramm.views.templates.TopBar
import ru.borz7zy.telegramm.views.ui.theme.Red99
import ru.borz7zy.telegramm.views.ui.theme.TelegramMTheme
import ru.borz7zy.telegramm.views.ui.theme.Icons
import ru.borz7zy.telegramm.views.ui.theme.vkSansDisplayFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TelegramMTheme {
                MainScreen()
            }
        }
    }
}

val hazeState = HazeState()

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentTitle = rememberSaveable { mutableStateOf("Чаты") }

    LaunchedEffect(navBackStackEntry?.destination?.route) {
        currentTitle.value = when (navBackStackEntry?.destination?.route) {
            BottomNavItem.Chats.route -> BottomNavItem.Chats.title
            else -> "TelegramM"
        }
    }

    Scaffold(
        topBar = { TopBar(currentTitle.value, hazeState) },
        bottomBar = { MyNavBar(navController)/*BottomNavigationBar(navController = navController)*/ }
    ) { innerPadding ->
        NavigationGraph(navController = navController, innerPadding)
    }
}

sealed class BottomNavItem(val route: String, val title: String) {
    object Chats : BottomNavItem("chats", "Чаты")
    object Settings : BottomNavItem("settings", "Настройки")
}

@Composable
fun BottomNavItemIcon(item: BottomNavItem): Painter {
    return when (item) {
        BottomNavItem.Chats -> Icons.message_outline()
        BottomNavItem.Settings -> Icons.settings_outline()
    }
}

@Composable
fun MyNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Chats,
        BottomNavItem.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
            .hazeEffect(state = hazeState, style = CupertinoMaterials.ultraThin()),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val isSelected = currentRoute == item.route
                Button(
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) { saveState = true }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Transparent),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = if (isSelected) Red99 else MaterialTheme.colorScheme.onSurface
                    ),
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(BottomNavItemIcon(item), contentDescription = item.title,
                            modifier = Modifier
                                .size(24.dp))
                        Text(text = item.title, color = MaterialTheme.colorScheme.onSurface,
                            fontFamily = vkSansDisplayFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp)
                    }
                }
            }
        }
    }
}


@Composable
fun NavigationGraph(navController: NavController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = BottomNavItem.Chats.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(BottomNavItem.Chats.route) {
            ChatsScreen(innerPadding, hazeState)
        }
        composable(BottomNavItem.Settings.route){
            SettingsScreen(innerPadding, hazeState)
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LightPreview(){
    TelegramMTheme(darkTheme = false) {
        MainScreen()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NightPreview(){
    TelegramMTheme(darkTheme = true) {
        MainScreen()
    }
}