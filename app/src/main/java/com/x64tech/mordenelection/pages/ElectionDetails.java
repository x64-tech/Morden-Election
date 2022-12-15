package com.x64tech.mordenelection.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.extras.SharedPrefHelper;
import com.x64tech.mordenelection.models.ElectionModel;

public class ElectionDetails extends AppCompatActivity {

    TextView detailElectionName, detailElectionDic, detailElectionStart;
    SharedPrefHelper sharedPrefHelper;
    Button multiButton;
    String phase;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_details);
        initVar();
    }

    private void initVar() {
        detailElectionName = findViewById(R.id.detailName);
        detailElectionDic = findViewById(R.id.detailDicription);
        detailElectionStart = findViewById(R.id.detailStartDate);
        multiButton = findViewById(R.id.multiButton);

        ElectionModel electionModel = (ElectionModel) getIntent().getSerializableExtra("election");
        phase = getIntent().getStringExtra("phase"); // TODO calculate phase with date in models
        sharedPrefHelper = new SharedPrefHelper(this);

        detailElectionName.setText(electionModel.getElectionName());
        detailElectionDic.setText(electionModel.getElectionDic());
        detailElectionStart.setText(electionModel.getElectionBegin());

        if (phase.equals("CurrentElection")){
            multiButton.setText("Give Vote !"); // TODO add give vote listener
        }else if (phase.equals("UpcomingElection")){
            multiButton.setText("Candidate"); // TODO add as a candidate listener
            multiButton.setOnClickListener(view -> {
                if (sharedPrefHelper.checkToken()) {
                    intent = new Intent(getApplicationContext(), ApplyCandidate.class);
                    intent.putExtra("electionID", electionModel.getElectionID());
                } else
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            });
        }else {
            multiButton.setVisibility(View.INVISIBLE);
        }
    }
}