package com.uday.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.uday.notesapp.screens.NoteDetailScreen
import com.uday.notesapp.screens.NoteListScreen
import com.uday.notesapp.screens.Screen
import com.uday.notesapp.ui.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NotesAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.NotesList.route
                ) {
                    composable(Screen.NotesList.route) {
                        NoteListScreen(
                            viewModel = hiltViewModel(),
                            onNoteClick = { noteId ->
                                navController.navigate(Screen.NoteDetail.createRoute(noteId))
                            },
                            onAddNoteClick = {
                                navController.navigate(Screen.NoteDetail.createRoute(0))
                            }
                        )
                    }
                    composable(
                        route = Screen.NoteDetail.route,
                        arguments = listOf(navArgument("noteId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                        NoteDetailScreen(
                            noteId = noteId,
                            viewModel = hiltViewModel(),
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
