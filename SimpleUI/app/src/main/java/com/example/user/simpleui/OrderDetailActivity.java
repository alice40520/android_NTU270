package com.example.user.simpleui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.nio.channels.AsynchronousCloseException;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Intent intent = getIntent();
        String note = intent.getStringExtra("note");
        String menuResult = intent.getStringExtra("menuResult");
        String storeInfo = intent.getStringExtra("storeInfo");

        TextView noteTextView = (TextView)findViewById(R.id.noteTextView);
        TextView menuResultTextView = (TextView)findViewById(R.id.menuResultTextView);
        TextView storeInfoTextView = (TextView)findViewById(R.id.storeInfoTextView);

        noteTextView.setText(note);
        storeInfoTextView.setText(storeInfo);

        List<String> menuResultList = order.getMenuResultList(menuResult);

        String text = "";
        if(menuResultList != null){
            for(String menuResults : menuResultList){
                text += menuResults = "\n";
            }
            menuResultTextView.setText(text);
        }

        /*public static */ class GeoCodingTask extends AsyncTask<String, Void, Bitmap>{  //error: class is not allowed to be public or static
            @Override
            protected Bitmap doInBackground(String... params) {
                String address = params[0];
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
            }
        }
    }
}
