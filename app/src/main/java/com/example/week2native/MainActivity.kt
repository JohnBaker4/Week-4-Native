package com.example.week2native

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.week2native.navigation.ROUTE_CALENDAR
import com.example.week2native.navigation.ROUTE_HOME
import com.example.week2native.navigation.ROUTE_SETTINGS
import com.example.week2native.view.SettingsScreen
import com.example.week2native.view.CalendarScreen
import com.example.week2native.view.HomeScreen
import com.example.week2native.ui.theme.Week2NativeTheme
import com.example.week2native.viewmodel.TaskViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val viewModel: TaskViewModel = viewModel()

            // Mahdollista laittaa tumma tila päälle jos valkoinen on palavaa magnesiumia
            var isDarkTheme by remember { mutableStateOf(false) }

            // Teema lähinnä vaikuttaa tuohon tummaan tilaan
            Week2NativeTheme(darkTheme = isDarkTheme) {

                // Aloitetaan HomeScreenistä mutta voidaan liikkua kalenteriin ja asetuksiin
                NavHost(
                    navController = navController,
                    startDestination = ROUTE_HOME
                ) {

                    composable(ROUTE_HOME) {
                        HomeScreen(
                            viewModel = viewModel,
                            onTaskClick = { id ->
                                viewModel.openTask(id)
                            },
                            onAddClick = {
                                viewModel.addTaskDialogVisible.value = true
                            },
                            onNavigateCalendar = {
                                navController.navigate(ROUTE_CALENDAR)
                            },
                            onNavigateSettings = {
                                navController.navigate(ROUTE_SETTINGS)
                            }
                        )
                    }

                    // Route kalenterille
                    composable(ROUTE_CALENDAR) {
                        CalendarScreen(
                            viewModel = viewModel,
                            onTaskClick = { id ->
                                viewModel.openTask(id)
                            },
                            onNavigateHome = {
                                navController.popBackStack()
                            },
                        )
                    }

                    // Route asetuksille ja asetus tummalle tilalle
                    composable(ROUTE_SETTINGS) {
                        SettingsScreen(
                            isDarkTheme = isDarkTheme,
                            onToggleTheme = {
                                isDarkTheme = !isDarkTheme
                            },
                            onNavigateHome = {
                                navController.popBackStack()
                            },
                            onNavigateCalendar = {
                                navController.navigate(ROUTE_CALENDAR)
                            }
                        )
                    }
                }
            }

        }
    }
}