package com.abdul_waheed.architecturecomponentcodinginflow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;
    private RecyclerView rvNotes;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNotes = findViewById(R.id.rv_notes);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        rvNotes.setHasFixedSize(true);

        noteAdapter = new NoteAdapter();
        rvNotes.setAdapter(noteAdapter);


        /*
        * we are going to create an instance of ViewModel. We are not going to use 'new' operator if we do to so we are always
        * going to take new instance every time. We need to ask Android System to provide an instance. It will provide new
        * instance if there is no already available.
        * of ==> by this viewModel knows which Acitivity life cycle it has to be attached
        * get ==> provide the ViewModel instance what we are asking for
        * */

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //Update our RecyclerView
                noteAdapter.setAllNotes(notes);
            }
        });
    }
}
