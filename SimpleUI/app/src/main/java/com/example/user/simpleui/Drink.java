package com.example.user.simpleui;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 2016/7/14.
 */
public class Drink {
    String drinkNmae;
    int mPrice = 0;
    int lPrice = 0;
    int imageId;

    // converting to json object
    public JSONObject getJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("name", drinkNmae);
            jsonObject.put("Price", mPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }

       return jsonObject;
    }
}
