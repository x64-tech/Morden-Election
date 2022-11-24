 package com.x64tech.mordenelection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.x64tech.mordenelection.databinding.ActivityMainBinding;
import com.x64tech.mordenelection.databinding.FragmentAlertBinding;
import com.x64tech.mordenelection.pages.AlertFragment;
import com.x64tech.mordenelection.pages.ElectionFragment;
import com.x64tech.mordenelection.pages.HomeFragment;
import com.x64tech.mordenelection.pages.ProfileFragment;

 public class MainActivity extends AppCompatActivity {


     ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        replace(new HomeFragment());
        mainBinding.navigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.btnHome:
                    replace(new HomeFragment());
                    break;
                case R.id.btnElection:
                    replace(new ElectionFragment());
                    break;
                case R.id.btnAlert:
                    replace(new AlertFragment());
                    break;
                case R.id.btnProfile:
                    replace(new ProfileFragment());
                    break;
            }
            return true;
        });
    }

    private void replace(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

}