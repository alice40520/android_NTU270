package com.example.user.simpleui;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.interfaces.DSAKey;
import java.util.List;

/**
 * Created by user on 2016/7/14.
 */

@ParseClassName("Drink")
public class Drink extends ParseObject {
//    private String drinkName;
//    private int mPrice = 0;
//    private int lPrice = 0;
    int imageId;

    public String getDrinkName(){
        return getString("drinkName");
    }

    public void setDrinkName(String drinkName){
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

    public ParseFile getImage(){
        return getParseFile("Image");
    }

    // converting to json object
    public JSONObject getJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("name", getDrinkName());
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
            drink.setDrinkName(jsonObject.getString("name"));
            drink.setmPrice(jsonObject.getInt("mPrice"));
            drink.setlPrice(jsonObject.getInt("lPrice"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return drink;
    }

    public static ParseQuery<Drink> getquery(){
        return ParseQuery.getQuery(Drink.class);
    }

    public static void syncDrinksFromRemote(final FindCallback<Drink> callback){
        Drink.getquery().findInBackground(new FindCallback<Drink>() {
            @Override
            public void done(final List<Drink> objects, ParseException e) {
                if(e == null){
                    Drink.unpinAllInBackground("Drink", new DeleteCallback() { // deleting all information saved in local device to make sure the information is synchronized
                        @Override
                        public void done(ParseException e) {
                            if(e == null){
                                Drink.pinAllInBackground("Drink", objects);
                            }
                            else{
                                Drink.getquery().fromLocalDatastore().findInBackground(callback);
                            }
                        }
                    });
                    callback.done(objects, e);
                }
            }
        });
    }
}
