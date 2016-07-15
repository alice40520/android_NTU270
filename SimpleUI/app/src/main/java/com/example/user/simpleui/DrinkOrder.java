package com.example.user.simpleui;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 2016/7/15.
 */
public class DrinkOrder {
    Drink drink;
    int mNumber = 0;
    int lNumber = 0;
    String sugar = "normal";
    String ice = "normal";
    String note = "";

    public DrinkOrder(Drink drink){
        this.drink = drink;
    }

    public String toData(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("drink", drink.getJsonObject().toString());
            jsonObject.put("mNumber", mNumber);
            jsonObject.put("lNumber", lNumber);
            jsonObject.put("sugar", sugar);
            jsonObject.put("ice", ice);
            jsonObject.put("note", note);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static DrinkOrder newInstanceWithData(String data){
        try {
            JSONObject jsonObject = new JSONObject(data);
            Drink drink = Drink.newInstanceFromData(jsonObject.getString("drink"));
            DrinkOrder drinkOrder = new DrinkOrder(drink);
            drinkOrder.lNumber = jsonObject.getInt("lNumber");
            drinkOrder.mNumber = jsonObject.getInt("mNumber");
            drinkOrder.sugar = jsonObject.getString("sugar");
            drinkOrder.ice = jsonObject.getString("ice");
            drinkOrder.note = jsonObject.getString("note");
            return drinkOrder;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
