package com.ravcode.instagramviewer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ravi on 9/16/14.
 */
public class Comment {
    public String text;
    public User user;

    public Comment(JSONObject commentJSON) throws JSONException {
        text = commentJSON.getString("text");
        user = new User(commentJSON.getJSONObject("from"));
    }
}
