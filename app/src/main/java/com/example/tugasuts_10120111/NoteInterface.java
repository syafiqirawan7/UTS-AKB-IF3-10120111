//10120111 - Syafiq Pramana Irawan - IF3

package com.example.tugasuts_10120111;

import android.database.Cursor;
import com.example.tugasuts_10120111.model.note;

public interface NoteInterface {

    public Cursor read();
    public boolean create(note note);
    public boolean update(note note);
    public boolean delete(String id);

}
