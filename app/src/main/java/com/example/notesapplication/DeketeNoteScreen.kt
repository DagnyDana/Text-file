package com.example.notesapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext

@Composable
fun DeleteNoteScreen(navController: NavHostController) {
    val context = LocalContext.current as MainActivity
    val notes = remember { mutableStateListOf(*context.getNotes().toTypedArray()) }

    LazyColumn {
        items(notes) { note ->
            ListItem(
                headlineContent = { Text(note) },
                modifier = Modifier.clickable {
                    context.deleteNoteFromStorage(note)
                    notes.remove(note)
                    navController.popBackStack()
                }
            )
        }
    }
}
