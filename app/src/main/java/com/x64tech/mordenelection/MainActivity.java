 package com.x64tech.mordenelection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.x64tech.mordenelection.databinding.ActivityMainBinding;
import com.x64tech.mordenelection.databinding.FragmentAlertBinding;
import com.x64tech.mordenelection.extras.Helper;
import com.x64tech.mordenelection.extras.NetworkIPHelper;
import com.x64tech.mordenelection.pages.AlertFragment;
import com.x64tech.mordenelection.pages.ElectionFragment;
import com.x64tech.mordenelection.pages.HomeFragment;
import com.x64tech.mordenelection.pages.ProfileFragment;

 public class MainActivity extends AppCompatActivity {

     NetworkIPHelper networkIPHelper;
     ActivityMainBinding mainBinding;
     AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        replace(new HomeFragment());

        networkIPHelper = new NetworkIPHelper(this);

        Toast.makeText(this, networkIPHelper.getIPString(), Toast.LENGTH_SHORT).show();


        if (networkIPHelper.checkIPString()) {

            alertDialogBuilder = new AlertDialog.Builder(this);

            final EditText editText = new EditText(this);
            editText.setHint("192.168.XXX.XXX");
            editText.setMaxLines(1);

            alertDialogBuilder.setTitle("Please Enter Network IP.");
            alertDialogBuilder.setView(editText);
            alertDialogBuilder.setPositiveButton("Ok", (dialogInterface, i) -> {
                if (editText.getText().toString().equals(""))
                    Toast.makeText(this, "Network IP in required..", Toast.LENGTH_SHORT).show();
                else
                    networkIPHelper.setIPString(editText.getText().toString());

            });
            alertDialogBuilder.setNegativeButton("Cancel", (dialogInterface, i) -> {
                dialogInterface.dismiss();
                finish();
            });
            alertDialogBuilder.show();
        }

        Toast.makeText(this, networkIPHelper.getIPString(), Toast.LENGTH_SHORT).show();

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