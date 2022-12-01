package com.x64tech.mordenelection.pages;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.x64tech.mordenelection.R;


public class ProfileFragment extends Fragment {

    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        button = view.findViewById(R.id.button3);

        button.setOnClickListener(view1 -> {
            Intent intent = new Intent(this.getContext(), LoginActivity.class);
            startActivity(intent);
        });

        return view;
    }
}