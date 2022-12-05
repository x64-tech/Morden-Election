package com.x64tech.mordenelection.extras;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHelper {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private static final String PORT = ":8080/";
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

    public String getToken(){
        return sharedPreferences.getString("TOKEN", "");
    }

    public Boolean checkToken(){
        return !sharedPreferences.getString("TOKEN", "").equals("");
    }

    public String getUserID(){
        return sharedPreferences.getString("USERID", "");
    }

    public SharedPreferences getSharedPreferences(){
        return sharedPreferences;
    }

    public void setSensitive(String userID, String token){
        editor.putString("USERID", userID);
        editor.putString("TOKEN", token);
        editor.commit();
    }

    public void setUserProfile(String name, String email, String username, String userDP,
                               boolean male, String cryptoID, String birthDate){
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("username", username);
        editor.putString("userDP", userDP);
        editor.putBoolean("male", male);
        editor.putString("cryptoID", cryptoID);
        editor.putString("birthDate", birthDate);
        editor.commit();
    }

    public void updateDP(String userDP){
        editor.putString("userDP", userDP);
        editor.commit();
    }

    public void updateProfile(String name, String email,
                              boolean male, String birthDate){
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putBoolean("male", male);
        editor.putString("birthDate", birthDate);
        editor.commit();
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }
}
