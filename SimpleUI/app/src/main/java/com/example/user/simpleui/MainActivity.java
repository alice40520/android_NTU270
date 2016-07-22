package com.example.user.simpleui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_CODE_DRINK_MENU_ACTIVITY = 0;

    TextView textView;
    EditText editText;
    RadioGroup radioGroup;
    ListView listView;
    Spinner spinner;

    String selectedDrink = "Bubble Tea";

    String menuResults = "";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    List<order> orders = new ArrayList<>(); // to create container for order
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
//    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.input);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        listView = (ListView) findViewById(R.id.menu);
        spinner = (Spinner) findViewById(R.id.spinner);
        sharedPreferences = getSharedPreferences("setting", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editText.setText(sharedPreferences.getString("editText", ""));

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String text = editText.getText().toString();
                editor.putString("editText", text);
                editor.commit();
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    submit(v);
                    return true;
                }
                return false;
            }
        });

        // TO-DO: debug
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                editor.putInt("spinner", position);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                selectedDrink = radioButton.getText().toString();
            }
        });

        String history = Utils.readFile(this, "history");
        String[] datas = history.split("\n");
        for (String data : datas) {
            order order = null;
            order = order.newInstanceWithData(data);
            if (order != null) {
                orders.add(order);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                order order = (order) parent.getAdapter().getItem(position);
                goToDetail(order);
            }
        });

        setUpListView();
        setUpSpinner();



        ParseObject parseObject = new ParseObject("Test"); //class name to the parse object
        parseObject.put("foo", "bar");
        parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Toast.makeText(MainActivity.this, "上傳成功", Toast.LENGTH_LONG);
            }
        });

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Test");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject object : objects) {
                        Toast.makeText(MainActivity.this, object.getString("foo"), Toast.LENGTH_LONG).show(); // shows item in "foo"
                    }
                }
            }
        });

        Log.d("Debug", "MainActivity OnCreate");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       // client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
        Log.d("Debug", "MainActivity OnStart");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Main Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.example.user.simpleui/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug", "MainActivity OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Debug", "MainActivity OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Main Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.example.user.simpleui/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
        Log.d("Debug", "MainActivity OnStop");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.disconnect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Debug", "MainActivity OnDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Debug", "MainActivity OnRestart");
    }

    public void setUpListView() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        FindCallback<order> callback = new FindCallback<order>() {
            @Override
            public void done(List<order> objects, ParseException e) {
                if (e == null) {
                    orders = objects;
                    OrderAdapter adapter = new OrderAdapter(MainActivity.this, orders);
                    listView.setAdapter(adapter);
                }
            }
        };

        if (networkInfo == null || !networkInfo.isConnected()) {
            order.getQuery().fromLocalDatastore().findInBackground(callback);
        } else {
            order.getOrdersFromRemote(callback);
        }

//        order.getOrdersFromRemote(new FindCallback<order>() {
//            @Override
//            public void done(List<order> objects, ParseException e) {
//                orders = objects;
//                OrderAdapter adapter = new OrderAdapter(MainActivity.this, orders);
//                listView.setAdapter(adapter);
//            }
//        });
    }

    public void setUpSpinner() {
        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("StoreInfo");
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                List<String> storeInfos = new ArrayList<String>();
                for(ParseObject object : objects){
                    String storeInfo = object.get("StoreName") + "," + object.get("StoreAddress");
                    storeInfos.add(storeInfo);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, storeInfos);
                spinner.setAdapter(adapter);
                spinner.setSelection(sharedPreferences.getInt("spinner", 0));

            }
        });
//        String[] data = getResources().getStringArray(R.array.storeInfo);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data);
//        spinner.setAdapter(adapter);
    }

    public void submit(View view) {

        String text = editText.getText().toString();

        //textView.setText(text); // input output

        order order = new order();
        order.setNote(text);
        order.setMenuResult(menuResults);
        order.setStoreInfo((String) spinner.getSelectedItem());

        order.pinInBackground("order"); // allows data to be saved on the local device when offline
        order.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                setUpListView();
            }
        });

        Utils.writeFile(this, "history", order.toData() + "\n");

        orders.add(order);

        editText.setText("");
        menuResults = "";
    }

    public void goToMenu(View view) {
        Intent intent = new Intent();
        intent.setClass(this, DrinkMenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_DRINK_MENU_ACTIVITY);
    }

    public void goToDetail(order order){
        Intent intent = new Intent();
        intent.setClass(this, OrderDetailActivity.class);
        intent.putExtra("note", order.getNote());
        intent.putExtra("storeInfo", order.getStoreInfo());
        intent.putExtra("menuResult", order.getMenuResult());
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_DRINK_MENU_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "訂購成功", Toast.LENGTH_SHORT).show();
                menuResults = data.getStringExtra("results");
            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "訂購取消", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
