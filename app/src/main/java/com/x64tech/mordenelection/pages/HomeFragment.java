package com.x64tech.mordenelection.pages;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.extras.SharedPrefHelper;


public class HomeFragment extends Fragment {
    SharedPrefHelper sharedPrefHelper;
    TextView textView;
    AlertDialog.Builder alertDialogBuilder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        sharedPrefHelper = new SharedPrefHelper(this.getContext());
        textView = view.findViewById(R.id.textView);
        return view;
    }
}
