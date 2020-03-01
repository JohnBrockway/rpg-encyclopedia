package com.johnbrockway.rpgencylopedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.johnbrockway.rpgencylopedia.data.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Note> notes;

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public NoteViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.note_text);
        }
    }

    public NotesAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.textView.setText(notes.get(position).text);
    }

    @Override
    public int getItemCount() {
        return notes == null ? 0 : notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }
}
