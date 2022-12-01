package com.x64tech.mordenelection.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.extras.NetworkIPHelper;

public class RegisterActivity extends AppCompatActivity {
    AutoCompleteTextView gender;
    Button registerBtn;
    EditText name, email, username, password;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    ArrayAdapter<String> genderAdapter;
    NetworkIPHelper networkIPHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initVar();

        registerBtn.setOnClickListener(view -> {
            regReq();
        });

    }

    private void initVar(){
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        username = findViewById(R.id.usernameReg);
        password = findViewById(R.id.passwordReg);
        gender = findViewById(R.id.gender);
        registerBtn = findViewById(R.id.register);
        networkIPHelper = new NetworkIPHelper(this);
        genderAdapter = new ArrayAdapter<>(this, R.layout.gender_dropdown, new String[]{"Male", "Female"});

        gender.setAdapter(genderAdapter);

        requestQueue = Volley.newRequestQueue(this);
    }

    private void regReq(){
        String url = "http://"+networkIPHelper.getIPString()+":8080/auth/test";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    Log.d("res", response.toString());
                    Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show();
        }, error -> {
            Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        });
        requestQueue.add(jsonObjectRequest);
    }

}