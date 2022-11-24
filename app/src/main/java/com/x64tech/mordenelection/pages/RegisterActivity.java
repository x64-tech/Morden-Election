package com.x64tech.mordenelection.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.extras.Helper;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    Button registerBtn;
    EditText name, email, username, password;
    RequestQueue requestQueue;
    JsonObjectRequest objectRequest;
    String url = "http://192.168.43.30:8080/public/election";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initVar();

        registerBtn.setOnClickListener(view -> {
            Toast.makeText(this, Helper.getHost(), Toast.LENGTH_LONG).show();
        });

    }

    private void initVar(){
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        username = findViewById(R.id.usernameReg);
        password = findViewById(R.id.passwordReg);

        registerBtn = findViewById(R.id.register);

        requestQueue = Volley.newRequestQueue(this);
    }

    private void regReq(){
        objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    Log.d("res", response.toString());
        }, error -> {
            Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        });
        requestQueue.add(objectRequest);
    }

}