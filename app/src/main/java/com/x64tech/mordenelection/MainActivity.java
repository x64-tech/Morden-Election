 package com.x64tech.mordenelection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import com.x64tech.mordenelection.databinding.ActivityMainBinding;
import com.x64tech.mordenelection.extras.SharedPrefHelper;
import com.x64tech.mordenelection.pages.AlertFragment;
import com.x64tech.mordenelection.pages.ElectionFragment;
import com.x64tech.mordenelection.pages.HomeFragment;
import com.x64tech.mordenelection.pages.ProfileFragment;

 public class MainActivity extends AppCompatActivity {

     SharedPrefHelper sharedPrefHelper;
     ActivityMainBinding mainBinding;
     AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        sharedPrefHelper = new SharedPrefHelper(this);

        Toast.makeText(this, sharedPrefHelper.getIPString(), Toast.LENGTH_SHORT).show();


        if (sharedPrefHelper.checkIPString()) {

            alertDialogBuilder = new AlertDialog.Builder(this);

            final EditText editText = new EditText(this);
            editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

            alertDialogBuilder.setTitle("Please Enter Network IP.");
            alertDialogBuilder.setView(editText);
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setPositiveButton("Ok", (dialogInterface, i) -> {
                if (editText.getText().toString().equals(""))
                    Toast.makeText(this, "Network IP in required..", Toast.LENGTH_SHORT).show();
                else
                    sharedPrefHelper.setIPString(editText.getText().toString());

            });
            alertDialogBuilder.setNegativeButton("Cancel", (dialogInterface, i) -> {
                dialogInterface.dismiss();
                finish();
            });
            alertDialogBuilder.show();
        }

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