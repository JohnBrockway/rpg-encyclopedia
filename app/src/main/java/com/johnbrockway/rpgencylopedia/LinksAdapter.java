package com.johnbrockway.rpgencylopedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.johnbrockway.rpgencylopedia.data.Entry;

import java.util.List;

public class LinksAdapter extends RecyclerView.Adapter<LinksAdapter.LinkViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Entry> links;

    public class LinkViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public LinkViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.link_text);
        }
    }

    public LinksAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public LinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_link, parent, false);
        return new LinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LinkViewHolder holder, int position) {
        holder.textView.setText(links.get(position).name);
    }

    @Override
    public int getItemCount() {
        return links == null ? 0 : links.size();
    }

    public void setLinks(List<Entry> links) {
        this.links = links;
        notifyDataSetChanged();
    }
}
