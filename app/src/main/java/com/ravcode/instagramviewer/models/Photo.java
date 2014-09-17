package com.ravcode.instagramviewer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ravi on 9/13/14.
 */
public class Photo {
    public String caption;
    public String imageURL;
    public int imageHeight;
    public int imageWidth;
    public int likesCount;
    public long createdTime;
    public ArrayList<Comment> comments;
    public User user;

    public Photo(JSONObject photoJSON) throws JSONException {
        user = new User(photoJSON.getJSONObject("user"));
        if (!photoJSON.isNull("caption")) {
            caption = photoJSON.getJSONObject("caption").getString("text");
        }

        JSONObject standardResolution = photoJSON.getJSONObject("images").getJSONObject("standard_resolution");
        imageURL = standardResolution.getString("url");
        imageWidth = standardResolution.getInt("width");
        imageHeight = standardResolution.getInt("height");
        likesCount = photoJSON.getJSONObject("likes").getInt("count");
        createdTime = photoJSON.getLong("created_time");
        comments = new ArrayList<Comment>();

        if (!photoJSON.isNull("comments")) {
            JSONArray commentsJSONArray = photoJSON.getJSONObject("comments").getJSONArray("data");
            for (int i = 0; i < commentsJSONArray.length(); i++) {
                if (i == 2) {
                    break;
                }

                comments.add(new Comment(commentsJSONArray.getJSONObject(i)));
            }
        }
    }
}
