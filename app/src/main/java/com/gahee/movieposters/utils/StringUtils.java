package com.gahee.movieposters.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static List<String> generateYoutubeThumbnailFromIds(List<String> videoIds){
        List<String> thumbnailLinks = new ArrayList<>();
        if(thumbnailLinks != null){
            thumbnailLinks.clear();
        }
        for(String id : videoIds){
            StringBuilder stringBuilder = new StringBuilder().append("http://i3.ytimg.com/vi/")
                    .append(id).append("/hqdefault.jpg");
            thumbnailLinks.add(stringBuilder.toString());
        }
        return thumbnailLinks;
    }

}
