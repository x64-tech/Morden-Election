package com.x64tech.mordenelection.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.models.ElectionModel;

import java.util.List;

public class PastElectionAdapter extends RecyclerView.Adapter<PastElectionAdapter.PastViewHolder> {

    Context context;
    List<ElectionModel> electionModels;
    public PastElectionAdapter(Context context, List<ElectionModel> electionModels) {
        super();
        this.context = context;
        this.electionModels=electionModels;
    }

    @NonNull
    @Override
    public PastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.past_election_card, parent, false);
        return new PastElectionAdapter.PastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PastViewHolder holder, int position) {
        ElectionModel electionModel = electionModels.get(position);
        holder.pastElectionName.setText(electionModel.getElectionName());
    }

    @Override
    public int getItemCount() {
        return electionModels.size();
    }

    public static class PastViewHolder extends RecyclerView.ViewHolder{

        TextView pastElectionName;
        public PastViewHolder(@NonNull View itemView) {
            super(itemView);
            pastElectionName = itemView.findViewById(R.id.pastElectionText);
        }
    }
}
