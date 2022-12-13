package com.x64tech.mordenelection.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.x64tech.mordenelection.R;

public class ApplyCandidate extends AppCompatActivity {

    Button applyBtn, chooseSymbolBtn;
    EditText candName, symbolName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_candidate);
        initVar();
    }

    private void initVar() {
        applyBtn = findViewById(R.id.btnApply);
        chooseSymbolBtn = findViewById(R.id.btnChooseSymbol);
        candName = findViewById(R.id.candName);
        symbolName = findViewById(R.id.imagename);
    }
}