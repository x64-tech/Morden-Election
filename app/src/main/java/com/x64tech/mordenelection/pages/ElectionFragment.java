package com.x64tech.mordenelection.pages;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.adapters.CurrentElectionAdapter;
import com.x64tech.mordenelection.adapters.PastElectionAdapter;
import com.x64tech.mordenelection.adapters.UpElectionAdapter;
import com.x64tech.mordenelection.extras.Others;
import com.x64tech.mordenelection.extras.SharedPrefHelper;
import com.x64tech.mordenelection.models.ElectionModel;

import org.json.JSONException;

import java.util.List;


public class ElectionFragment extends Fragment {

    RecyclerView currentElectionRecycle, upcomingElectionRecycle, pastElectionRecycle;
    SharedPrefHelper sharedPrefHelper;
    RequestQueue requestQueue;
    JsonObjectRequest electionRequest;
    AlertDialog.Builder alertDialog;
    ProgressDialog progressDialog;
    TextView upcoming;
    CurrentElectionAdapter adapter1;
    UpElectionAdapter adapter2;
    PastElectionAdapter adapter3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVAR();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_election, container, false);
        initGraphical(view);
        getElectionData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getElectionData();
    }

    private void initVAR() {
        sharedPrefHelper = new SharedPrefHelper(this.getContext());
        requestQueue = Volley.newRequestQueue(this.getContext());
        alertDialog = new AlertDialog.Builder(this.getContext());

        progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setTitle("loading");
        progressDialog.setMessage("Wait while getting data...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void initGraphical(View view) {
        currentElectionRecycle = view.findViewById(R.id.currentElectionRecycle);
        upcomingElectionRecycle = view.findViewById(R.id.upcomingElectionRecycle);
        pastElectionRecycle = view.findViewById(R.id.pastElectionRecycle);
        upcoming = view.findViewById(R.id.upcomingTextView);
    }

    private void getElectionData(){
        progressDialog.show();
        String url = sharedPrefHelper.getHostAddress()+"election/allPhaseElection";
        electionRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        progressDialog.setMessage("Wait while Setting data...");
                        displayRecycles(
                                Others.mapElection(response.getJSONArray("currentElection")),
                                Others.mapElection(response.getJSONArray("upcomingElection")),
                                Others.mapElection(response.getJSONArray("pastElection")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    progressDialog.dismiss();
                    alertDialog.setTitle("Error");
                    if (error.networkResponse != null)
                        alertDialog.setMessage(new String(error.networkResponse.data));
                    else
                        alertDialog.setMessage(error.toString());
                    alertDialog.show();
        });
        requestQueue.add(electionRequest);
    }

    public void displayRecycles(List<ElectionModel> currentElection,
                                List<ElectionModel> upcomingElection,
                                List<ElectionModel> pastElection){
        if (currentElection.size()==0){
            currentElectionRecycle.setVisibility(View.GONE);
        }else {
            adapter1 = new CurrentElectionAdapter(this.getContext(), currentElection);
            currentElectionRecycle.setAdapter(adapter1);
            currentElectionRecycle.setLayoutManager(
                    new LinearLayoutManager(this.getContext(),
                            RecyclerView.HORIZONTAL, false));
        }

        if (upcomingElection.size()==0){
            upcomingElectionRecycle.setVisibility(View.GONE);
            upcoming.setVisibility(View.GONE);
        }else {
            adapter2 = new UpElectionAdapter(this.getContext(), upcomingElection);
            upcomingElectionRecycle.setAdapter(adapter2);
            upcomingElectionRecycle.setLayoutManager(
                    new LinearLayoutManager(this.getContext(),
                            RecyclerView.HORIZONTAL, false));
        }

        System.out.println(pastElection);
        adapter3 = new PastElectionAdapter(this.getContext(), pastElection);
        pastElectionRecycle.setAdapter(adapter3);
        pastElectionRecycle.setLayoutManager(
                new LinearLayoutManager(this.getContext(),
                        RecyclerView.VERTICAL, false));
        progressDialog.dismiss();
    }
}