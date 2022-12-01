package com.x64tech.mordenelection.extras;

import android.content.Context;
import android.content.SharedPreferences;

public class NetworkIPHelper {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public NetworkIPHelper(Context context) {
        sharedPreferences  = context.getSharedPreferences("MESharedPre", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public Boolean checkIPString(){
        return sharedPreferences.getString("NETWORK_IP", "").equals("");
    }

    public String getIPString(){
        return sharedPreferences.getString("NETWORK_IP", "");
    }

    public void setIPString(String ipString){
        editor.putString("NETWORK_IP", ipString);
        editor.commit();
    }

}
