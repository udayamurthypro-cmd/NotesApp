package com.uday.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.uday.notesapp.repository.NotesRepository
import com.uday.notesapp.roomdb.Note
import com.uday.notesapp.roomdb.NoteDb
import com.uday.notesapp.ui.theme.NotesAppTheme
import com.uday.notesapp.viewmodel.NoteViewModel
import com.uday.notesapp.viewmodel.NoteViewModelFactory
import com.uday.notesapp.screens.DisplayNotesList
import androidx.core.graphics.toColorInt
import com.uday.notesapp.screens.DisplayDialog
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {  viewModel.insertNote(note1) }

        setContent {
            NotesAppTheme {

                Scaffold(
                    floatingActionButton = { myFAB(viewModel) }
                ) {

                }

              val notes by viewModel.allNotes.observeAsState(emptyList())
                DisplayNotesList(notes)
            }
        }
    }



}

@Composable
fun myFAB(viewModel: NoteViewModel){
    FloatingActionButton(onClick = {

        DisplayDialog(viewModel)
    },
        containerColor = androidx.compose.ui.graphics.Color.Blue,
        contentColor = androidx.compose.ui.graphics.Color.White) {

    }

}