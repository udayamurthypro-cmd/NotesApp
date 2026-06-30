package com.uday.notesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uday.notesapp.repository.NotesRepository
import com.uday.notesapp.roomdb.Note
import kotlinx.coroutines.launch

class NoteViewModel(private val respository: NotesRepository) : ViewModel() {

    val allNotes: LiveData<List<Note>> = respository.allNotes

    fun insertNote(note: Note) {
        viewModelScope.launch {
            respository.insertNote(note)
        }
    }

}