//10120111 - Syafiq Pramana Irawan - IF3

package com.example.tugasuts_10120111.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasuts_10120111.R;
import com.example.tugasuts_10120111.model.note;
import com.example.tugasuts_10120111.view.activity.AddNoteActivity;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<note> notes;

    public NoteAdapter(List<note> notes) {
        this.notes = notes;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_note, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(notes.get(position).getTitle());
        holder.desc.setText(notes.get(position).getDesc());
        holder.date.setText(notes.get(position).getDate());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), AddNoteActivity.class);
            intent.putExtra("Note", notes.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_note);
            desc = itemView.findViewById(R.id.desc_note);
            date = itemView.findViewById(R.id.date_note);
        }
    }

}
