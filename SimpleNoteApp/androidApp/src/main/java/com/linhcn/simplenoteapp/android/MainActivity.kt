package com.linhcn.simplenoteapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.linhcn.simplenoteapp.android.note.detail.NoteDetailScreen
import com.linhcn.simplenoteapp.android.note.list.NoteListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "note_list") {
                        composable("note_list") {
                            NoteListScreen(
                                navController = navController
                            )
                        }
                        composable(
                            "note_detail/{note_id}",
                            arguments = listOf(navArgument(name = "note_id") {
                                type = NavType.LongType
                                defaultValue = -1L
                            })
                        ) {
                            NoteDetailScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}