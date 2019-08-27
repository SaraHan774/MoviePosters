package com.gahee.movieposters.utils;

import android.util.Log;

public class StringUtils {

    public static String generateYoutubeThumbnailFromId(String videoId){
            StringBuilder stringBuilder = new StringBuilder().append("http://i3.ytimg.com/vi/")
                    .append(videoId).append("/hqdefault.jpg");
        Log.d("videourls" ,  stringBuilder.toString());
        return stringBuilder.toString();
    }

}
