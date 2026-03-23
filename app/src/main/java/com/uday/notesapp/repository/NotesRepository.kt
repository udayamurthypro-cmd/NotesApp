package com.uday.notesapp.repository

import androidx.lifecycle.LiveData
import com.uday.notesapp.roomdb.Note
import com.uday.notesapp.roomdb.NoteDao

class NotesRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insertNote(note: Note) {
        noteDao.insert(note)
    }
}