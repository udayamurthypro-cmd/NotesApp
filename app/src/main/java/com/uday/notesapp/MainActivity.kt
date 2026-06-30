package com.uday.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import com.uday.notesapp.repository.NotesRepository
import com.uday.notesapp.roomdb.Note
import com.uday.notesapp.roomdb.NoteDb
import com.uday.notesapp.ui.theme.NotesAppTheme
import com.uday.notesapp.viewmodel.NoteViewModel
import com.uday.notesapp.viewmodel.NoteViewModelFactory
import com.uday.notesapp.screens.DisplayNotesList
import androidx.core.graphics.toColorInt
import com.uday.notesapp.screens.DisplayDialog

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val roomDB = NoteDb.getInstance(applicationContext)
        val repository = NotesRepository(roomDB.noteDao())
        val viewModelFactory = NoteViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]

        val note1 = Note(0,"Android Course","Welcome to Android Course, Kindly share and rate us 5 stars",
            "#595977".toColorInt())

        viewModel.insertNote(note1)

        setContent {
            NotesAppTheme {

                Scaffold(floatingActionButton = { MyFAB(viewModel) }){
                    it.calculateTopPadding()
                    val notes by viewModel.allNotes.observeAsState(emptyList())
                    DisplayNotesList(notes)
                }
            }
        }
    }



}

@Composable
fun MyFAB(viewModel: NoteViewModel){

    var showDialog by remember {
        mutableStateOf(false)
    }

    DisplayDialog(viewModel, showDialog) {
        showDialog = false
    }

    FloatingActionButton(onClick = { showDialog = true },
        containerColor = androidx.compose.ui.graphics.Color.Blue,
        contentColor = androidx.compose.ui.graphics.Color.White) {
        Icon(imageVector = Icons.Filled.Add,"Add Note")
    }

}