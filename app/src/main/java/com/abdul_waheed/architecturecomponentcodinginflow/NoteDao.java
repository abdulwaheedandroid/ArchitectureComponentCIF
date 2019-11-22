package com.abdul_waheed.architecturecomponentcodinginflow;

import android.widget.LinearLayout;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/*
* Generally it is a good practice to have once DAO per entity. To make a DAO, interface/abstract class
* should be annotated with DAO annotation. We define the methods that is going to be relative to this
* table.
* */
@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();
}
