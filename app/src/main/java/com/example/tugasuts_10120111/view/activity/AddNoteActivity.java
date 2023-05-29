//10120111 - Syafiq Pramana Irawan - IF3

package com.example.tugasuts_10120111.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugasuts_10120111.NoteInterface;
import com.example.tugasuts_10120111.R;
import com.example.tugasuts_10120111.database.DatabaseHelper;
import com.example.tugasuts_10120111.model.note;

import java.util.Date;
public class AddNoteActivity extends AppCompatActivity {
    ImageButton button;
    EditText editTitle;
    //    EditText editCategory;
    EditText editDesc;
    Button addButton;
    Button deleteButton;
    TextView titleAdd;

    private NoteInterface noteInterface;
    note note = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        getSupportActionBar().hide();
        note = (note) getIntent().getSerializableExtra("Note");
        button = findViewById(R.id.back);
        editTitle = findViewById(R.id.title);
        editDesc = findViewById(R.id.txt_desc);
        addButton = findViewById(R.id.buttonAdd);
        deleteButton = findViewById(R.id.buttonDelete);
        titleAdd = findViewById(R.id.txt_add);
        noteInterface = new DatabaseHelper(this);

        button.setOnClickListener(v -> {
            finish();
        });

        if (note == null){
            deleteButton.setVisibility(View.GONE);

            addButton.setOnClickListener(v -> {
                if (editTitle.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Judul Catatan tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editDesc.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Isi Catatan tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                }
                Date d = new Date();
                CharSequence date = DateFormat.format("EEEE, d MMM yyyy HH:mm", d.getTime());
                note n = new note(
                        d.getTime() + "",
                        editTitle.getText().toString(),
                        editDesc.getText().toString() ,
                        date + ""
                );

                noteInterface.create(n);
                finish();
                Toast.makeText(this, "Catatan berhasil ditambah", Toast.LENGTH_SHORT).show();
            });
        } else {
            editTitle.setText(note.getTitle());
            editDesc.setText(note.getDesc());

            deleteButton.setVisibility(View.VISIBLE);
            titleAdd.setText("Edit Catatan");

            addButton.setOnClickListener(v -> {
                if (editTitle.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Judul Catatan tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (editDesc.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Isi Catatan tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                }

                Date d = new Date();
                CharSequence date = DateFormat.format("EEEE, d MMMM yyyy HH:mm",d.getTime());

                note.setTitle(editTitle.getText().toString());
                note.setDesc(editDesc.getText().toString());
                note.setDate("terakhir diedit " + date + "");
                noteInterface.update(note);
                finish();
                Toast.makeText(this, "Catatan berhasil diedit", Toast.LENGTH_SHORT).show();
            });
        }

        deleteButton.setOnClickListener(v-> {
            noteInterface.delete(note.getId());
            finish();
            Toast.makeText(this, "Catatan berhasil dihapus", Toast.LENGTH_SHORT).show();
        });
    }

}
