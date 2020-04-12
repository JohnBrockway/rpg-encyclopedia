package com.johnbrockway.rpgencylopedia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.johnbrockway.rpgencylopedia.data.Entry;

import java.util.List;

public class EntriesAdapter extends RecyclerView.Adapter<EntriesAdapter.EntryViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Entry> entries;

    public class EntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;

        public EntryViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.entry_text);

            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int entryIcon = entries.get(getAdapterPosition()).icon;
            int entryId = entries.get(getAdapterPosition()).id;
            String entryName = entries.get(getAdapterPosition()).name;
            Intent intent = new Intent(view.getContext(), EntryActivity.class);
            intent.putExtra(
                    view.getContext().getString(R.string.intent_entry_id),
                    entryId);
            intent.putExtra(
                    view.getContext().getString(R.string.intent_entry_name),
                    entryName);
            intent.putExtra(
                    view.getContext().getString(R.string.intent_entry_icon),
                    entryIcon);
            view.getContext().startActivity(intent);
            view.getContext().startActivity(intent);
        }
    }

    public EntriesAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_entry, parent, false);
        return new EntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {
        holder.textView.setText(entries.get(position).name);
    }

    @Override
    public int getItemCount() {
        return entries == null ? 0 : entries.size();
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
        notifyDataSetChanged();
    }
}
