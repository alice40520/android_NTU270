package com.example.user.simpleui;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 2016/7/14.
 */
public class order {
    String note;
    String menuResult;
    String storeInfo;

    public int totalNumber(){
        if(menuResult == null || menuResult.equals("")){
            return 0;
        }
        int totalNumber = 0;
        try {
            JSONArray jsonArray = new JSONArray(menuResult);
            for(int i = 0; i < jsonArray.length(); i++){
                String data = jsonArray.getString(i);
                DrinkOrder drinkOrder = DrinkOrder.newInstanceWithData(data);
                totalNumber = totalNumber + drinkOrder.mNumber + drinkOrder.lNumber;
            }
            return totalNumber;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
