package com.uday.notesapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("DELETE FROM notes_table WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM notes_table")
    fun getAllNotes() : LiveData<List<Note>>


}
