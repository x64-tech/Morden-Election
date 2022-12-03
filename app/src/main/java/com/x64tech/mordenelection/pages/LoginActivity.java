package com.x64tech.mordenelection.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.extras.SharedPrefHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextView regText;
    Button loginButton;
    EditText username, password;
    SharedPrefHelper sharedPrefHelper;
    RequestQueue requestQueue;
    JsonObjectRequest loginRequest, profileRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initVar();

        regText.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(view -> {
            login();
        });
    }

    private void initVar(){
        regText = findViewById(R.id.regText);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        sharedPrefHelper = new SharedPrefHelper(this);
        requestQueue = Volley.newRequestQueue(this);
    }

    private void login(){
        String url = sharedPrefHelper.getHostAddress()+"auth/login";
        JSONObject postData = new JSONObject();
        try {
            postData.put("username", username.getText().toString());
            postData.put("password", password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        loginRequest = new JsonObjectRequest(Request.Method.POST, url, postData,
                response -> {
                    try {
                        sharedPrefHelper.setToken(response.getString("token"));
                        sharedPrefHelper.setUserID(response.getString("userID"));
                        Toast.makeText(this, "logged in", Toast.LENGTH_SHORT).show();
                        profile();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                    finish();
        });
        requestQueue.add(loginRequest);
    }

    private void profile(){
        String url = sharedPrefHelper.getHostAddress()+"user/profile/"+sharedPrefHelper.getUserID();
        profileRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                response -> {
                    try {
                        sharedPrefHelper.setUserProfile(response.getString("name"),
                                response.getString("email"),
                                response.getString("username"),
                                response.getString("userDP"),
                                response.getBoolean("male"),
                                response.getString("cryptoID"),
                                response.getString("birthDate"));
                        Toast.makeText(this, "user info saved", Toast.LENGTH_SHORT).show();
                        finishAffinity();
                        Toast.makeText(this, "Logged in, restart app", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                Throwable::printStackTrace){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer "+sharedPrefHelper.getToken());
                return params;
            }
        };
        requestQueue.add(profileRequest);
    }

}