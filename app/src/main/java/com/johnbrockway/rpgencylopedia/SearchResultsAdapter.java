package com.johnbrockway.rpgencylopedia;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.johnbrockway.rpgencylopedia.data.Category;
import com.johnbrockway.rpgencylopedia.data.Entry;
import com.johnbrockway.rpgencylopedia.data.Item;
import com.johnbrockway.rpgencylopedia.data.Note;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ResultViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Item> results;

    public class ResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;

        public ResultViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.result_text);

            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int icon = results.get(getAdapterPosition()).getIcon();
            int id = results.get(getAdapterPosition()).getId();
            String title = results.get(getAdapterPosition()).getTitle();
            Intent intent;
            switch (results.get(getAdapterPosition()).getItemType()) {
                case CATEGORY:
                    intent = new Intent(view.getContext(), EntryListActivity.class);
                    intent.putExtra(
                            view.getContext().getString(R.string.intent_category_id),
                            id);
                    intent.putExtra(
                            view.getContext().getString(R.string.intent_category_icon),
                            icon);
                    intent.putExtra(
                            view.getContext().getString(R.string.intent_category_name),
                            title);
                    break;
                case ENTRY:
                    intent = new Intent(view.getContext(), EntryActivity.class);
                    intent.putExtra(
                            view.getContext().getString(R.string.intent_entry_id),
                            id);
                    intent.putExtra(
                            view.getContext().getString(R.string.intent_entry_icon),
                            icon);
                    intent.putExtra(
                            view.getContext().getString(R.string.intent_entry_name),
                            title);
                    break;
                case NOTE:
                    intent = new Intent(view.getContext(), EntryActivity.class);
                    break;
                 default:
                    intent = new Intent(view.getContext(), CategoryListActivity.class);
            }
            view.getContext().startActivity(intent);
        }
    }

    public SearchResultsAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.results = new ArrayList<>();
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_search_result, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        holder.textView.setText(results.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.size();
    }

    public void addCategories(List<Category> categories) {
        this.results.addAll(categories);
        notifyDataSetChanged();
    }

    public void addEntries(List<Entry> entries) {
        this.results.addAll(entries);
        notifyDataSetChanged();
    }

    public void addNotes(List<Note> notes) {
        this.results.addAll(notes);
        notifyDataSetChanged();
    }
}
