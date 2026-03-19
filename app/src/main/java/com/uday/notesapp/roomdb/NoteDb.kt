package com.uday.notesapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDb : RoomDatabase() {

    abstract var noteDao : NoteDao

    companion object {
        @Volatile
        private var INSTANCE : NoteDb? = null

        fun getInstance(context: Context) : NoteDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context = context,
                        NoteDb::class.java,
                        "notes_database"
                    ).build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}