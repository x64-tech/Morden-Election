package com.x64tech.mordenelection.extras;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

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
}
