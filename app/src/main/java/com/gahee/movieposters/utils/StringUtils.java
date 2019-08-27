package com.gahee.movieposters.utils;

import android.util.Log;

public class StringUtils {

    public static String generateYoutubeThumbnailFromId(String videoId){
            StringBuilder stringBuilder = new StringBuilder().append("http://i3.ytimg.com/vi/")
                    .append(videoId).append("/hqdefault.jpg");
        Log.d("videourls" ,  stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static String getYoutubeWatchUrlFromIds(String videoId){
        if(videoId != null || (videoId != null ? videoId.equals("") : false)){
            return "http://www.youtube.com/watch?v=" + videoId;
        }
        else{
            return "";
        }
    }

}
