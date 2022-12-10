package com.x64tech.mordenelection.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.models.ElectionModel;
import com.x64tech.mordenelection.pages.ElectionDetails;

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
        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ElectionDetails.class);
            intent.putExtra("election", electionModel);
            intent.putExtra("phase", "PastElection");
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return electionModels.size();
    }

    public static class PastViewHolder extends RecyclerView.ViewHolder{

        TextView pastElectionName;
        MaterialCardView cardView;
        public PastViewHolder(@NonNull View itemView) {
            super(itemView);
            pastElectionName = itemView.findViewById(R.id.pastElectionText);
            cardView = itemView.findViewById(R.id.pastElectionCard);
        }
    }
}
