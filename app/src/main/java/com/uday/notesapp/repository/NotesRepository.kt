package com.uday.notesapp.repository

import androidx.lifecycle.LiveData
import com.uday.notesapp.roomdb.Note
import com.uday.notesapp.roomdb.NoteDao
import javax.inject.Inject

class NotesRepository @Inject constructor(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insertNote(note: Note) {
        noteDao.insert(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.update(note)
    }

    suspend fun deleteNote(id: Int) {
        noteDao.delete(id)
    }
}