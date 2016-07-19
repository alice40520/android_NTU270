package com.example.user.simpleui;

import android.app.Application;

import com.parse.Parse;
import com.parse.Parse.Configuration;
import com.parse.ParseObject;

/**
 * Created by user on 2016/7/19.
 */
public class SimpleUIApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(order.class);
        Parse.initialize(new Configuration.Builder(this)
                .applicationId("MxGjYKtfSKnDnIxA1pMrFoN3pwXmReRCdHr1yGOB")
                .server("https://parseapi.back4app.com/")
                .clientKey("OduCTTvyqaIzQfNkLIDa09NDydTkIpmPqC08xazN")
                .build());
    }
}
