package com.x64tech.mordenelection.pages;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.extras.SharedPrefHelper;

import org.json.JSONObject;


public class ElectionFragment extends Fragment {

    RecyclerView currentElectionRecycle, upcomingElection, pastElection;
    SharedPrefHelper sharedPrefHelper;
    RequestQueue requestQueue;
    JsonObjectRequest electionRequest;
    AlertDialog.Builder alertDialog;
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
        upcomingElection = view.findViewById(R.id.upcomingElectionRecycle);
        pastElection = view.findViewById(R.id.pastElectionRecycle);

        upcomingElection.setLayoutManager(new LinearLayoutManager(this.requireContext()));
    }

    private void getElectionData(){
        String url = sharedPrefHelper.getHostAddress()+"election/allPhaseElection";
        electionRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    Toast.makeText(this.requireContext(), "getting res", Toast.LENGTH_SHORT).show();
                }, error -> {
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage(new String(error.networkResponse.data));
                    alertDialog.show();
        });
        requestQueue.add(electionRequest);
    }
}