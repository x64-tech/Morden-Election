package com.x64tech.mordenelection.pages;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.extras.SharedPrefHelper;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class ProfileFragment extends Fragment {

    private Button interButton;
    private ConstraintLayout proSettingLayout, Information, settings, logout;
    private ImageView userDP;
    private TextView user_name, user_email;
    private SharedPrefHelper sharedPrefHelper;
    private Intent intent;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVAR(this.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        graphicalINIT(view);

        if (sharedPrefHelper.checkToken()) {

            getUserData();

            interButton.setOnClickListener(view1 -> {
                intent = new Intent(this.getContext(), LoginActivity.class);
                startActivity(intent);
            });

            Information.setOnClickListener(view12 -> {

            });

            settings.setOnClickListener(view12 -> {

            });

            logout.setOnClickListener(view12 -> {

            });
        }else {
            interButton.setText("Login");
            proSettingLayout.setVisibility(View.INVISIBLE);
            interButton.setOnClickListener(view1 -> {
                intent = new Intent(this.getContext(), LoginActivity.class);
                startActivity(intent);
            });
        }

        return view;
    }

    private void graphicalINIT(View view){
        interButton = view.findViewById(R.id.interButton);
        proSettingLayout = view.findViewById(R.id.proSettingLayout);
        Information = view.findViewById(R.id.Information);
        settings = view.findViewById(R.id.settings);
        logout = view.findViewById(R.id.logout);
        userDP = view.findViewById(R.id.userDP);
        user_name = view.findViewById(R.id.user_name);
        user_email = view.findViewById(R.id.user_email);
    }

    private void initVAR(Context context){
        sharedPrefHelper = new SharedPrefHelper(context);
        requestQueue = Volley.newRequestQueue(context);
    }

    private void getUserData(){
        String url = sharedPrefHelper.getHostAddress()+"user/profile/"+sharedPrefHelper.getUserID();
        System.out.println(url);
        jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                response -> {
                    try {
                        user_name.setText(response.getString("name"));
                        user_email.setText(response.getString("email"));
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

        requestQueue.add(jsonObjectRequest);
    }
}