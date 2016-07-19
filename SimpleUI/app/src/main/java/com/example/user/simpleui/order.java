package com.example.user.simpleui;

import android.widget.RatingBar;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by user on 2016/7/14.
 */

@ParseClassName("order")
public class order extends ParseObject {
    private String note;
    private String menuResult;
    private String storeInfo;

    public String getNote(){
        String note = getString("note");
        if(note == null){
            return "";
        }
        return note;
    }

    public void setNote(String note){
        put("note", note);
    }

    public String getMenuResult(){
        String menuResult = getString("menuResult");
        if(menuResult == null){
            return "";
        }
        return menuResult;
    }

    public void setMenuResult(String menuResult){
        put("menuResult", menuResult);
    }

    public String getStoreInfo(){
        String storeInfo = getString("storeInfo");
        if(storeInfo == null){
            return "";
        }
        return storeInfo;
    }

    public void setStoreInfo(String storeInfo){
        put("storeInfo", storeInfo);
    }

    public int totalNumber(){
        if(getMenuResult() == null || getMenuResult().equals("")){
            return 0;
        }
        int totalNumber = 0;
        try {
            JSONArray jsonArray = new JSONArray(getMenuResult());
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

    public static ParseQuery<order> getQuery(){
        return  ParseQuery.getQuery(order.class);
    }

    public static void getOrdersFromRemote(final FindCallback<order> callback){
        getQuery().findInBackground(new FindCallback<order>() {
            @Override
            public void done(List<order> objects, ParseException e) {
                if(e == null){
                    order.pinAllInBackground("order", objects);
                }
                callback.done(objects, e);
            }
        });
    }

    public String toData(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("note", getNote());
            jsonObject.put("menuResult", getMenuResult());
            jsonObject.put("storeInfo", getStoreInfo());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static order newInstanceWithData(String data){

        order order = new order();
        try {
            JSONObject jsonObject = new JSONObject(data);
            order.setNote(jsonObject.getString("note"));
            order.setMenuResult(jsonObject.getString("menuResult"));
            order.setStoreInfo(jsonObject.getString("storeInfo"));
            return order;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
