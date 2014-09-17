package com.ravcode.instagramviewer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ravi on 9/16/14.
 */
public class User {
    public String profileURL;
    public String username;

    public User(JSONObject user) throws JSONException {
        username = user.getString("username");
        if (!user.isNull("profile_picture")) {
            profileURL = user.getString("profile_picture");
        }
    }
}
