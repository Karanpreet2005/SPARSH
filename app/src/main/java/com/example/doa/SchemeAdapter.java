package com.example.doa;

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

    @NonNull
    @Override
    public SchemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scheme_item, parent, false);
        return new SchemeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchemeViewHolder holder, int position) {
        Scheme scheme = schemeList.get(position);
        holder.bind(scheme);
    }

    @Override
    public int getItemCount() {
        return schemeList.size();
    }

    public class SchemeViewHolder extends RecyclerView.ViewHolder {
        private final TextView schemeName;
        private final ImageView schemeThumbnail;

        public SchemeViewHolder(@NonNull View itemView) {
            super(itemView);
            schemeName = itemView.findViewById(R.id.scheme_name);
            schemeThumbnail = itemView.findViewById(R.id.scheme_thumbnail);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Scheme scheme = schemeList.get(position);
                    mainActivity.openSchemeDetail(scheme);
                }
            });
        }

        public void bind(Scheme scheme) {
            schemeName.setText(scheme.getName());
            schemeThumbnail.setImageResource(scheme.getImage());
        }
    }
}
