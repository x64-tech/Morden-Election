package com.x64tech.mordenelection.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
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
import com.x64tech.mordenelection.extras.SharedPrefHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    AutoCompleteTextView gender;
    Button registerBtn;
    EditText name, email, username, password, birthdate;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    ProgressDialog progressDialog;
    ArrayAdapter<String> genderAdapter;
    SharedPrefHelper sharedPrefHelper;
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
        birthdate = findViewById(R.id.birthdate);
        registerBtn = findViewById(R.id.register);

        sharedPrefHelper = new SharedPrefHelper(this);
        genderAdapter = new ArrayAdapter<>(this, R.layout.gender_dropdown, new String[]{"Male", "Female"});

        gender.setAdapter(genderAdapter);

        requestQueue = Volley.newRequestQueue(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("loading");
        progressDialog.setMessage("Wait while registering you...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void regReq(){
        progressDialog.show();
        String url = sharedPrefHelper.getHostAddress()+"auth/register";
        JSONObject postData = new JSONObject();
        try {
            postData.put("name", name.getText().toString());
            postData.put("email", email.getText().toString());
            postData.put("male", gender.getText().toString().equals("Male"));
            postData.put("birthDate", birthdate.getText().toString());
            postData.put("username", username.getText().toString());
            postData.put("password", password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData,
                response -> {
                    progressDialog.dismiss();
                    finish();
        }, error -> {
            Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
            error.printStackTrace();
        });
        requestQueue.add(jsonObjectRequest);
    }

}