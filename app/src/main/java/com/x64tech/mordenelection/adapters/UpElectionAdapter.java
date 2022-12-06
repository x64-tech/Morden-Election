package com.x64tech.mordenelection.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UpElectionAdapter extends RecyclerView.Adapter<UpElectionAdapter.UpElectionViewHolder> {

    Context context;
    public UpElectionAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public UpElectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UpElectionViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class UpElectionViewHolder extends RecyclerView.ViewHolder {

        public UpElectionViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
