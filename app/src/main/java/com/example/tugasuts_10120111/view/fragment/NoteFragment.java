//10120111 - Syafiq Pramana Irawan - IF3

package com.example.tugasuts_10120111.view.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasuts_10120111.NoteInterface;
import com.example.tugasuts_10120111.R;
import com.example.tugasuts_10120111.adapter.NoteAdapter;
import com.example.tugasuts_10120111.database.DatabaseHelper;
import com.example.tugasuts_10120111.model.note;
import com.example.tugasuts_10120111.view.activity.AddNoteActivity;
import com.example.tugasuts_10120111.view.activity.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
public class NoteFragment extends Fragment {

    private MainActivity mainActivity;
    private List<note> note;
    private NoteInterface noteInterface;
    private RecyclerView recyclerView;
    private com.example.tugasuts_10120111.adapter.NoteAdapter noteAdapter;
    private FloatingActionButton addButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView =  inflater.inflate(R.layout.fragment_note, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        mainActivity.getSupportActionBar().show();

        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.mynote);
        addButton = view.findViewById(R.id.button_add);
        addButton.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AddNoteActivity.class));
        });

        read();
    }

    @Override
    public void onResume() {
        super.onResume();
        read();
    }

    private void read() {
        note = new ArrayList<note>();
        noteInterface = new DatabaseHelper(getContext());
        Cursor cursor = noteInterface.read();
        if (cursor.moveToFirst()){
            do {
                note n = new note(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );

                note.add(n);
            } while (cursor.moveToNext());
        }

        noteAdapter = new NoteAdapter(note);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }

}
