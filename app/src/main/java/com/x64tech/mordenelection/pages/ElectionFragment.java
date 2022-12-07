package com.x64tech.mordenelection.pages;

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
import com.x64tech.mordenelection.extras.Others;
import com.x64tech.mordenelection.extras.SharedPrefHelper;
import com.x64tech.mordenelection.models.ElectionModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;


public class ElectionFragment extends Fragment {

    RecyclerView currentElectionRecycle, upcomingElectionRecycle, pastElectionRecycle;
    SharedPrefHelper sharedPrefHelper;
    RequestQueue requestQueue;
    JsonObjectRequest electionRequest;
    AlertDialog.Builder alertDialog;
    List<ElectionModel> currentElection, upcomingElection, pastElection;
    TextView current, upcoming, past;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVAR();
        getElectionData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_election, container, false);
        initGraphical(view);
        return view;
    }
    private void initVAR() {
        sharedPrefHelper = new SharedPrefHelper(this.requireContext());
        requestQueue = Volley.newRequestQueue(this.requireContext());
        alertDialog = new AlertDialog.Builder(this.requireContext());
    }
    private void initGraphical(View view) {
        currentElectionRecycle = view.findViewById(R.id.currentElectionRecycle);
        upcomingElectionRecycle = view.findViewById(R.id.upcomingElectionRecycle);
        pastElectionRecycle = view.findViewById(R.id.pastElectionRecycle);

        upcomingElectionRecycle.setLayoutManager(new LinearLayoutManager(this.requireContext()));
    }

    private void getElectionData(){
        String url = sharedPrefHelper.getHostAddress()+"election/allPhaseElection";
        electionRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray upcomingElection = response.getJSONArray("upcomingElection");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage(new String(error.networkResponse.data));
                    alertDialog.show();
        });
        requestQueue.add(electionRequest);
    }
}