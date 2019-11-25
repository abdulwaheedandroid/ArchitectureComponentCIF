package com.abdul_waheed.architecturecomponentcodinginflow;

import android.app.Application;
import android.app.ListActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/*
* View Model persist data. It services configuration changes. View Model destroyes when activity destroys or fragment gets detached.
* AndroidViewModel is a subclass of ViewModel. The difference between these 2 is in AndroidViewModel we
* pass Application in constructor which can be used wherever context is needed. Developer should not store context of an
* Activity or view that references an Activity in the View-Model. As ViewModel gets destroyed when activity is destroyed.
* If we keep reference it will have reference to the Activity and we will have a memory leak.
* Our Activity will have reference only to the ViewModel but not reference of repository. So we are going to prove wrapper
* methods to our ViewModel so we will be able to perform operations
* */

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    public void insert(Note note) {
        noteRepository.insert(note);
    }

    public void update(Note note) {
        noteRepository.update(note);
    }

    public void delete(Note note) {
        noteRepository.delete(note);
    }

    public void deleteAll() {
        noteRepository.deleteAll();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
