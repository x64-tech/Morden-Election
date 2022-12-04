package com.x64tech.mordenelection.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.extras.SharedPrefHelper;

public class MyInformation extends AppCompatActivity {

    private SharedPrefHelper sharedPrefHelper;
    private EditText cryptoID, userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);
        initVar();

        cryptoID.setText(sharedPrefHelper.getSharedPreferences().getString("cryptoID", ""));
        userID.setText(sharedPrefHelper.getUserID());
    }

    private void initVar() {
        cryptoID = findViewById(R.id.cryptoID);
        userID = findViewById(R.id.userID);

        sharedPrefHelper = new SharedPrefHelper(this);
    }
}