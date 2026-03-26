package com.uday.notesapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.uday.notesapp.roomdb.Note
import com.uday.notesapp.viewmodel.NoteViewModel

@Composable
fun DisplayDialog(noteViewModel: NoteViewModel) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val selectedColor by remember { mutableIntStateOf(Color.Blue.toArgb()) }


    AlertDialog(
        onDismissRequest = {},
        title = { Text("Enter Note")},
        text = {
            Column(){
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Note Title") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Note description") }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        confirmButton = {
            Button(onClick = {
                val note = Note(0,title,description,selectedColor)
            }) {
                Text("Save Note")
            }
        }
    )
}