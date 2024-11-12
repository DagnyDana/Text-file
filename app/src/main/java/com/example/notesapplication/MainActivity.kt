@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.notesapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete

class MainActivity : ComponentActivity() {
    private val notesList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesApp()
        }
    }

    fun saveNoteToStorage(title: String, content: String) {
        notesList.add("$title: $content")
    }

    fun deleteNoteFromStorage(note: String) {
        notesList.remove(note)
    }

    fun getNotes(): List<String> {
        return notesList
    }
}

@Composable
fun NotesApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("add_note") { AddNoteScreen(navController) }
        composable("delete_note") { DeleteNoteScreen(navController) }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Notes App") },
                actions = {
                    IconButton(onClick = { navController.navigate("add_note") }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add Note")
                    }
                    IconButton(onClick = { navController.navigate("delete_note") }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete Note")
                    }
                }
            )
        }
    ) { paddingValues ->
        NoteList(Modifier.padding(paddingValues))
    }
}

@Composable
fun NoteList(modifier: Modifier = Modifier) {
    val notes = remember { mutableStateListOf("Sample Note 1", "Sample Note 2") }

    LazyColumn(modifier = modifier) {
        items(notes) { note ->
            ListItem(
                headlineContent = { Text(note) },
                modifier = Modifier.clickable {
                }
            )
        }
    }
}
