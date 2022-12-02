package com.x64tech.mordenelection.extras;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHelper {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private final String PORT = ":8080/";
    public SharedPrefHelper(Context context) {
        sharedPreferences  = context.getSharedPreferences("MESharedPre", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Network IP
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

    public String getHostAddress(){
        return "http://"+getIPString()+PORT;
    }

    // token
    public void setToken(String token){
        editor.putString("TOKEN", token);
        editor.commit();
    }

    public String getToken(){
        return sharedPreferences.getString("TOKEN", "");
    }

    public Boolean checkToken(){
        return sharedPreferences.getString("TOKEN", "").equals("");
    }
}
