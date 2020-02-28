package com.johnbrockway.rpgencylopedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.johnbrockway.rpgencylopedia.data.Category;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Category> categories;

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public CategoryViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.category_text);
        }
    }

    public CategoriesAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.textView.setText(categories.get(position).listLabel);
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size();
    }
}
