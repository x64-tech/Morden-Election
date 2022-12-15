package com.x64tech.mordenelection.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.extras.Others;
import com.x64tech.mordenelection.extras.SharedPrefHelper;
import com.x64tech.mordenelection.models.ElectionModel;

import java.util.List;

public class CandidateAdapter
        extends RecyclerView.Adapter<CandidateAdapter.CandidateViewHolder> {

    Context context;
    List<ElectionModel.Candidates> candidates;
    SharedPrefHelper sharedPrefHelper;
    public CandidateAdapter(Context context, List<ElectionModel.Candidates> candidates,
                            SharedPrefHelper sharedPrefHelper) {
        this.context = context;
        this.candidates = candidates;
        this.sharedPrefHelper = sharedPrefHelper;
    }

    @NonNull
    @Override
    public CandidateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.candidate_list_overview, parent, false);
        return new CandidateAdapter.CandidateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CandidateViewHolder holder, int position) {
        ElectionModel.Candidates candidate = candidates.get(position);
        holder.cand_Name.setText(candidate.getName());
        holder.cand_id.setText(candidate.getUserID());
        // TODO get votes of candidates
        Others.glideRequest(sharedPrefHelper, holder.cand_image);
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public static class CandidateViewHolder extends RecyclerView.ViewHolder{
        TextView cand_Name, cand_id, cand_votes;
        ImageView cand_image;
        public CandidateViewHolder(@NonNull View itemView) {
            super(itemView);
            cand_Name = itemView.findViewById(R.id.cand_name);
            cand_id = itemView.findViewById(R.id.cand_id);
            cand_votes = itemView.findViewById(R.id.cand_votes);
            cand_image = itemView.findViewById(R.id.cand_dp);
        }
    }
}
