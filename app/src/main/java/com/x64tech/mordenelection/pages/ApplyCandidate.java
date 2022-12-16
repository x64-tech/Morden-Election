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
import android.widget.Button;
import android.widget.EditText;

import com.x64tech.mordenelection.R;
import com.x64tech.mordenelection.extras.Others;
import com.x64tech.mordenelection.extras.SharedPrefHelper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApplyCandidate extends AppCompatActivity {

    String electionID;
    Button applyBtn, chooseSymbolBtn;
    EditText candName, symbolName;
    SharedPrefHelper sharedPrefHelper;
    OkHttpClient client;
    ProgressDialog progressDialog;
    AlertDialog.Builder alertDialog;
    Uri symbolUri;
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

        electionID = getIntent().getStringExtra("electionID");

        sharedPrefHelper = new SharedPrefHelper(this);
        client = new OkHttpClient().newBuilder().build();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("loading");
        progressDialog.setMessage("Wait while logging you...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setPositiveButton("Ok", (dialogInterface, i) -> {finish();});

        chooseSymbolBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intent = Intent.createChooser(intent, "Choose Symbol");
            resultLauncher.launch(intent);
        });

        applyBtn.setOnClickListener(view -> {
            makeReq();
        });
    }

    private void makeReq(){
        final String url = sharedPrefHelper.getHostAddress() + "election/applyForCandidate";
        progressDialog.show();
        RequestBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("symbol",
                        Others.getFileName(getApplicationContext(), symbolUri),
                        RequestBody.create(Others.getByteData(getApplicationContext(), symbolUri)))
                .addFormDataPart("name", candName.getText().toString())
                //.addFormDataPart("cryptoID", "") TODO
                .addFormDataPart("userID", sharedPrefHelper.getUserID())
                .addFormDataPart("electionID", electionID)
                .build();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + sharedPrefHelper.getToken())
                .post(multipartBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                System.out.println("request failed");
                runOnUiThread(()->{
                    progressDialog.dismiss();
                    alertDialog.setMessage(e.toString());
                    alertDialog.show();
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response res) {
                runOnUiThread(() -> {
                    progressDialog.dismiss();
                    if (res.isSuccessful())
                        alertDialog.setMessage("Successfully applied for candidate");
                    else
                        alertDialog.setMessage("Failed to apply for candidate");
                    alertDialog.show();
                });
            }
        });
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        symbolUri = data.getData();
                        symbolName.setText(Others.getFileName(this, symbolUri));
                    }
                }
            }
    );
}