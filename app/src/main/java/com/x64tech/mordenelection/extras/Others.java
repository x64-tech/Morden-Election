package com.x64tech.mordenelection.extras;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.x64tech.mordenelection.models.ElectionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Others {
    public static void glideRequest(Context context, ImageView imageView){
        SharedPrefHelper sharedPrefHelper = new SharedPrefHelper(context.getApplicationContext());
        GlideUrl url1 = new GlideUrl(
                sharedPrefHelper.getHostAddress() + sharedPrefHelper.getSharedPreferences()
                        .getString("userDP", ""),
                new LazyHeaders.Builder().addHeader("Authorization", "Bearer " + sharedPrefHelper.getToken())
                .build());
        Glide.with(context)
                .load(url1)
                .into(imageView);
    }

    public static List<ElectionModel> mapElection(JSONArray electionArray) throws JSONException {
        List<ElectionModel> electionModels = new ArrayList<>();
        for (int i = 0; i<= electionArray.length()-1; i++){
            JSONObject jsonObject = electionArray.getJSONObject(i);
            electionModels.add(new ElectionModel(
                    jsonObject.getString("electionID"),
                    jsonObject.getString("electionName"),
                    jsonObject.getString("electionDic"),
                    mappedCandi(jsonObject.getJSONArray("candidates")),
                    jsonObject.getString("electionBegin"),
                    jsonObject.getString("electionEnd")
            ));
        }
        return electionModels;
    }

    public static List<ElectionModel.Candidates> mappedCandi(JSONArray cand) throws JSONException {
        List<ElectionModel.Candidates> candidates = new ArrayList<>();
        for (int i = 0; i<= cand.length()-1; i++){
            System.out.println(cand.getJSONObject(i));
            JSONObject jsonObject = cand.getJSONObject(i);
            candidates.add(
                    new ElectionModel.Candidates(
                            jsonObject.getString("name"),
                            jsonObject.getString("cryptoID"),
                            jsonObject.getString("userID"),
                            jsonObject.getString("electionID")
                    )
            );
        }
        return candidates;
    }
}
