package com.x64tech.mordenelection.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.models.ElectionModel;

import java.util.List;

public class CurrentElectionAdapter
        extends RecyclerView.Adapter<CurrentElectionAdapter.CurrentViewHolder> {

    Context context;
    List<ElectionModel> electionModels;
    public CurrentElectionAdapter(Context context, List<ElectionModel> electionModels) {
        super();
        this.context = context;
        this.electionModels=electionModels;
    }

    @NonNull
    @Override
    public CurrentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.current_election_card, parent, false);
        return new CurrentElectionAdapter.CurrentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentViewHolder holder, int position) {
        ElectionModel electionModel = electionModels.get(position);
        holder.currentElectionName.setText(electionModel.getElectionName());
    }

    @Override
    public int getItemCount() {
        return electionModels.size();
    }

    public static class CurrentViewHolder extends RecyclerView.ViewHolder{

        TextView currentElectionName, currentElectionPercent;
        Button currentElectionButton;
        ProgressBar currentElectionProgress;
        public CurrentViewHolder(@NonNull View itemView) {
            super(itemView);
            currentElectionName = itemView.findViewById(R.id.currentElectionName);
            currentElectionPercent = itemView.findViewById(R.id.currentElectionPercent);
            currentElectionButton = itemView.findViewById(R.id.currentElectionButton);
            currentElectionProgress = itemView.findViewById(R.id.currentElectionProgress);
        }
    }
}
