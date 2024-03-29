package com.x64tech.mordenelection.pages;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;

import android.content.Intent;
import android.net.Uri;
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
import com.google.android.material.imageview.ShapeableImageView;
import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.extras.Others;
import com.x64tech.mordenelection.extras.SharedPrefHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EditProfile extends AppCompatActivity {

    Button saveButton;
    AutoCompleteTextView editGender;
    EditText editName, editEmail, editDOB;
    ArrayAdapter<String> genderAdapter;
    SharedPrefHelper sharedPrefHelper;
    RequestQueue requestQueue;
    JsonObjectRequest updateRequest;
    OkHttpClient client;
    AlertDialog.Builder alertDialog, tempDialog;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initVARS();

        saveButton.setOnClickListener(view -> update());

    }

    private void initVARS() {
        sharedPrefHelper = new SharedPrefHelper(this);

        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);
        editGender = findViewById(R.id.edit_gender);
        editDOB = findViewById(R.id.edit_dob);

        requestQueue = Volley.newRequestQueue(this);
        client = new OkHttpClient().newBuilder().build();
        saveButton = findViewById(R.id.edit_button);

        genderAdapter = new ArrayAdapter<>(this, R.layout.gender_dropdown, new String[]{"Male", "Female"});

        editGender.setAdapter(genderAdapter);

        editName.setText(sharedPrefHelper.getSharedPreferences().getString("name", ""));
        editEmail.setText(sharedPrefHelper.getSharedPreferences().getString("email", ""));
        editDOB.setText(sharedPrefHelper.getSharedPreferences().getString("birthDate", ""));

        if (sharedPrefHelper.getSharedPreferences().getBoolean("male", false))
            editGender.setText(genderAdapter.getItem(0));
        else
            editGender.setText(genderAdapter.getItem(1));

        alertDialog = new AlertDialog.Builder(this);
        tempDialog = new AlertDialog.Builder(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("loading");
        progressDialog.setMessage("Wait while updating you...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void update() {
        progressDialog.show();
        String url = sharedPrefHelper.getHostAddress() + "user/profile";
        JSONObject putData = new JSONObject();
        try {
            putData.put("userID", sharedPrefHelper.getUserID());
            putData.put("name", editName.getText().toString());
            putData.put("email", editEmail.getText().toString());
            putData.put("male", editGender.getText().toString().equals("Male"));
            putData.put("birthDate", editDOB.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        updateRequest = new JsonObjectRequest(Request.Method.PUT, url, putData,
                response -> {
                    try {
                        sharedPrefHelper.updateProfile(response.getString("name"),
                                response.getString("email"),
                                response.getBoolean("male"),
                                response.getString("birthDate"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    progressDialog.dismiss();
                    Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();
                }, error -> {
            progressDialog.dismiss();
            alertDialog.setTitle("Error");
            alertDialog.setMessage(new String(error.networkResponse.data));
            alertDialog.show();
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer " + sharedPrefHelper.getToken());
                return params;
            }
        };
        requestQueue.add(updateRequest);
    }
}