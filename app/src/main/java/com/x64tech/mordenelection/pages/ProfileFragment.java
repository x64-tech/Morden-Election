package com.x64tech.mordenelection.pages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.extras.SharedPrefHelper;


public class ProfileFragment extends Fragment {

    private Button interButton;
    private ImageView userDP;
    private TextView user_name, user_email;
    private SharedPrefHelper sharedPrefHelper;
    private Intent intent;

    RequestQueue requestQueue;


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

            setUserData();

            interButton.setOnClickListener(view1 -> {
                intent = new Intent(this.getContext(), EditProfile.class);
                startActivity(intent);
            });

            view.findViewById(R.id.information).setOnClickListener(view12 -> {
                intent = new Intent(this.getContext(), MyInformation.class);
                startActivity(intent);
            });

            view.findViewById(R.id.settings).setOnClickListener(view12 -> {

            });

            view.findViewById(R.id.logout).setOnClickListener(view12 -> logout());
        }else {
            interButton.setText("Login");
            view.findViewById(R.id.proSettingLayout).setVisibility(View.INVISIBLE);
            interButton.setOnClickListener(view1 -> {
                intent = new Intent(this.getContext(), LoginActivity.class);
                startActivity(intent);
            });
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sharedPrefHelper.checkToken())
            setUserData();
    }

    private void graphicalINIT(View view){
        interButton = view.findViewById(R.id.interButton);
        userDP = view.findViewById(R.id.userDP);
        user_name = view.findViewById(R.id.user_name);
        user_email = view.findViewById(R.id.user_email);
    }

    private void initVAR(Context context){
        sharedPrefHelper = new SharedPrefHelper(context);
        requestQueue = Volley.newRequestQueue(context);
    }

    private void setUserData(){
        SharedPreferences preferences = sharedPrefHelper.getSharedPreferences();
        user_name.setText(preferences.getString("name", ""));
        user_email.setText(preferences.getString("email", ""));
        Glide.with(this)
                .load(sharedPrefHelper.getHostAddress()+preferences.getString("userDP", ""))
                .into(userDP);
    }

    private void logout(){
        AlertDialog.Builder logoutBuilder = new AlertDialog.Builder(this.requireContext());
        logoutBuilder.setTitle("Logout");
        logoutBuilder.setMessage("Are you sure to logout ?");
        logoutBuilder.setPositiveButton("Yes", (dialogInterface, i) -> {
            sharedPrefHelper.logout();
            dialogInterface.dismiss();
            requireActivity().finish();
        });
        logoutBuilder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
        logoutBuilder.show();
    }
}