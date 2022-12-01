package com.x64tech.mordenelection.extras;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class Helper {

    public static String PORT=":8080";

    public static String getHost(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
    }

    public static String getHostAddress(Context context){
        return "http://"+getHost(context)+PORT;
    }

    public static void getNetworkIP(Context context){
        NetworkIPHelper networkIPHelper = new NetworkIPHelper(context);
        final EditText ipText = new EditText(context.getApplicationContext());

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context.getApplicationContext());
        alertDialogBuilder.setTitle("Please Enter Network IP.");
        alertDialogBuilder.setView(ipText);
        alertDialogBuilder.setPositiveButton("Ok", (dialogInterface, i) -> {
                    networkIPHelper.setIPString(ipText.getText().toString());
                });
        alertDialogBuilder.setNegativeButton("Cancel", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });
        alertDialogBuilder.show();

    }
}
