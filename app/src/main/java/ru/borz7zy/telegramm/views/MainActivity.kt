package ru.borz7zy.telegramm.views

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.haze.HazeState
import ru.borz7zy.telegramm.views.screens.ChatsScreen
import ru.borz7zy.telegramm.views.templates.TopBar
import ru.borz7zy.telegramm.views.ui.theme.TelegramMTheme

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
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        NavigationGraph(navController = navController, innerPadding)
    }
}

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String){
    object Chats : BottomNavItem("chats", Icons.Filled.Email, "Чаты")
}

@Composable
fun BottomNavigationBar(navController: NavController){
    val items = listOf(
        BottomNavItem.Chats
    )

    NavigationBar {
        val navBarStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBarStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = {Icon(item.icon, contentDescription = item.title)},
                label = {
                    Text(text = item.title)
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route){
                        navController.graph.startDestinationRoute?.let{ route->
                            popUpTo(route){
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
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