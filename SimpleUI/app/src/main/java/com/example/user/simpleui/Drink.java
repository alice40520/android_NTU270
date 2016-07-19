package com.example.user.simpleui;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 2016/7/14.
 */

@ParseClassName("Drink")
public class Drink extends ParseObject {
//    private String drinkName;
//    private int mPrice = 0;
//    private int lPrice = 0;
    int imageId;

    public String getDrinkNmae(){
        return getString("drinkName");
    }

    public void setDrinkNmae(String drinkName){
        put("drinkName", drinkName);
    }

    public int getmPrice(){
        return getInt("mPrice");
    }

    public void setmPrice(int mPrice){
        put("mPrice", mPrice);
    }

    public int getlPrice(){
        return getInt("lPrice");
    }

    public void setlPrice(int lPrice){
        put("lPrice", lPrice);
    }

    // converting to json object
    public JSONObject getJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("name", getDrinkNmae());
            jsonObject.put("mPrice", getmPrice());
            jsonObject.put("lPrice", getlPrice());
        } catch (JSONException e) {
            e.printStackTrace();
        }

       return jsonObject;
    }

    public static Drink newInstanceFromData(String data){
        Drink drink = new Drink();
        try {
            JSONObject jsonObject = new JSONObject(data);
            drink.setDrinkNmae(jsonObject.getString("name"));
            drink.setmPrice(jsonObject.getInt("mPrice"));
            drink.setlPrice(jsonObject.getInt("lPrice"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return drink;
    }
}
