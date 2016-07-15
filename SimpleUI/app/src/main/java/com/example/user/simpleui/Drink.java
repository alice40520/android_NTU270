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
            jsonObject.put("lPrice", lPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }

       return jsonObject;
    }

    public static Drink newInstanceFromData(String data){
        Drink drink = new Drink();
        try {
            JSONObject jsonObject = new JSONObject(data);
            drink.drinkNmae = jsonObject.getString("name");
            drink.mPrice = jsonObject.getInt("mPrice");
            drink.lPrice = jsonObject.getInt("lPrice");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return drink;
    }
}
