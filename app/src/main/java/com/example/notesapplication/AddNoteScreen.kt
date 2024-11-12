package com.example.notesapplication

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@Composable
fun AddNoteScreen(navController: NavHostController) {
    var noteTitle by remember { mutableStateOf("") }
    var noteContent by remember { mutableStateOf("") }
    val context = LocalContext.current as MainActivity

    Column {
        TextField(
            value = noteTitle,
            onValueChange = { noteTitle = it },
            label = { Text("Note Title") }
        )
        TextField(
            value = noteContent,
            onValueChange = { noteContent = it },
            label = { Text("Note Content") }
        )
        Button(onClick = {
            if (noteTitle.isEmpty() || noteContent.isEmpty()) {
                Toast.makeText(context, "Please enter both title and content", Toast.LENGTH_SHORT).show()
            } else {
                context.saveNoteToStorage(noteTitle, noteContent)
                navController.popBackStack()
            }
        }) {
            Text(text = "Add Note")
        }
    }
}
