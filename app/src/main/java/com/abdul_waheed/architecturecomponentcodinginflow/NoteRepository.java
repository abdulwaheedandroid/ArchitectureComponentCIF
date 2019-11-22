package com.abdul_waheed.architecturecomponentcodinginflow;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private enum Operations {
        INSERT, UPDATE, DELETE, DELTE_ALL
    }


    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Context context) {
        NoteDataBase noteDataBase = NoteDataBase.getInstance(context);
        noteDao = noteDataBase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao, Operations.INSERT).execute(note);
    }

    public void update(Note note) {
        new InsertNoteAsyncTask(noteDao, Operations.UPDATE).execute(note);
    }

    public void delete(Note note) {
        new InsertNoteAsyncTask(noteDao, Operations.DELETE).execute(note);
    }

    public void deleteAll() {
        new InsertNoteAsyncTask(noteDao, Operations.DELTE_ALL).execute(new Note("", "", 0));
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;
        private Operations operations;

        public InsertNoteAsyncTask(NoteDao noteDao, Operations operations) {
            this.noteDao = noteDao;
            this.operations = operations;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            switch (operations) {
                case INSERT:
                    noteDao.insert(notes[0]);
                    break;

                case DELETE:
                    noteDao.delete(notes[0]);
                    break;

                case UPDATE:
                    noteDao.update(notes[0]);
                    break;

                case DELTE_ALL:
                    noteDao.deleteAllNotes();
                    break;
            }

            return null;
        }
    }
}
