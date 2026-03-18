package com.uday.notesapp.roomdb

import androidx.room.Database
import androidx.room.Room

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDb {

    var instance = Room.databaseBuilder(
        context = context.applicationContext,
        NoteDb::class.java,
        "notes_database").build()

}