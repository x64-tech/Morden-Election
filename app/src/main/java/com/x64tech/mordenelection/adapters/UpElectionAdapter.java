package com.x64tech.mordenelection.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.models.ElectionModel;
import com.x64tech.mordenelection.pages.ElectionDetails;

import java.util.List;

public class UpElectionAdapter extends RecyclerView.Adapter<UpElectionAdapter.UpElectionViewHolder> {

    Context context;
    List<ElectionModel> electionModels;
    public UpElectionAdapter(Context context, List<ElectionModel> electionModels) {
        super();
        this.context = context;
        this.electionModels=electionModels;
    }

    @NonNull
    @Override
    public UpElectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.upcoming_election_card, parent, false);
        return new UpElectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpElectionViewHolder holder, int position) {
        ElectionModel electionModel = electionModels.get(position);
        holder.electionName.setText(electionModel.getElectionName());
        holder.electionDec.setText(electionModel.getElectionDic());
        holder.readMore.setOnClickListener(view -> {
            Intent intent = new Intent(context, ElectionDetails.class);
            intent.putExtra("election", electionModel);
            intent.putExtra("phase", "UpcomingElection");
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return electionModels.size();
    }

    public static class UpElectionViewHolder extends RecyclerView.ViewHolder {

        TextView electionName, electionDec;
        Button readMore;
        public UpElectionViewHolder(@NonNull View itemView) {
            super(itemView);
            electionName = itemView.findViewById(R.id.electionName);
            electionDec = itemView.findViewById(R.id.electionDec);
            readMore = itemView.findViewById(R.id.readMore);
        }
    }
}
