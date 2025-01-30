package com.example.doa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SchemeAdapter extends RecyclerView.Adapter<SchemeAdapter.SchemeViewHolder> {

    private final List<Scheme> schemeList;
    private final MainActivity mainActivity;

    public SchemeAdapter(MainActivity mainActivity, List<Scheme> schemeList) {
        this.mainActivity = mainActivity;
        this.schemeList = schemeList;
    }

    @Override
    public SchemeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scheme_item, parent, false);
        return new SchemeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SchemeViewHolder holder, int position) {
        Scheme scheme = schemeList.get(position);
        holder.schemeName.setText(scheme.getName());
        holder.schemeThumbnail.setImageResource(scheme.getImage());

        holder.itemView.setOnClickListener(v -> mainActivity.openSchemeDetail(scheme));
    }

    @Override
    public int getItemCount() {
        return schemeList.size();
    }

    public static class SchemeViewHolder extends RecyclerView.ViewHolder {
        TextView schemeName;
        ImageView schemeThumbnail;

        public SchemeViewHolder(View itemView) {
            super(itemView);
            schemeName = itemView.findViewById(R.id.scheme_name);
            schemeThumbnail = itemView.findViewById(R.id.scheme_thumbnail);
        }
    }
}
