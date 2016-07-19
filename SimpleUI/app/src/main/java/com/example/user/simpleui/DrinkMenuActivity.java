package com.example.user.simpleui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

public class DrinkMenuActivity extends AppCompatActivity implements DrinkOrderDialog.OnDrinkOrderListener {

    TextView totalTextView;
    TextView priceTextView;
    ListView drinkMenuListView;

    String[] drinkNames = { "冬瓜紅茶", "玫瑰鹽奶蓋紅茶", "珍珠紅茶拿鐵", "紅茶拿鐵" };
    int[] mPrices = {25, 35, 45, 35};
    int[] lPrices = {35, 45, 55, 45};
    int[] imageId = {R.drawable.drink1, R.drawable.drink2, R.drawable.drink3, R.drawable.drink4};

    List<Drink> drinks = new ArrayList<>();
    List<DrinkOrder> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);
        Log.d("Debug", "DrinkMenunActivity onCreate");
        setData();

        totalTextView = (TextView)findViewById(R.id.totalTextView);
        priceTextView = (TextView)findViewById(R.id.priceTextView);
        drinkMenuListView = (ListView)findViewById(R.id.drinkMenuListView);

        setUpDrinkMenuListView();
    }

    private void setData(){
        for(int i = 0; i < drinkNames.length; i++){
            Drink drink = new Drink();
            drink.setDrinkNmae(drinkNames[i]);
            drink.setmPrice(mPrices[i]);
            drink.setlPrice(lPrices[i]);
            drink.imageId = imageId[i];
            drinks.add(drink);
        }
    }

    private void setUpDrinkMenuListView(){
        DrinkAdapter adapter = new DrinkAdapter(this, drinks);
        drinkMenuListView.setAdapter(adapter);

        drinkMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DrinkAdapter drinkAdapter = (DrinkAdapter) parent.getAdapter();
                Drink drink = (Drink) drinkAdapter.getItem(position);
                showDrinkOrderDialog(drink);
            }
        });
    }

    public void showDrinkOrderDialog(Drink drink){
        DrinkOrder drinkOrder = new DrinkOrder(drink);
        for(DrinkOrder order : orders){
            if(order.drink.getDrinkNmae().equals(drink.getDrinkNmae())){
                drinkOrder = order;
                break;
            }
        }

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DrinkOrderDialog dialog = DrinkOrderDialog.newInstance(drinkOrder);
        Fragment prev = getFragmentManager().findFragmentByTag("DrinkOrderDialog");
        if(prev != null){
            fragmentTransaction.remove(prev);
        }
        fragmentTransaction.addToBackStack(null);

        dialog.show(fragmentTransaction, "DrinkOrderDialog");
    }

    public void updateTotal(){
        int total = 0;
        for(DrinkOrder order: orders){
            total = total + order.mNumber * order.drink.getmPrice() + order.lNumber * order.drink.getlPrice();
        }
        priceTextView.setText(String.valueOf(total));
    }

    public void done(View view){
        Intent intent = new Intent();

        JSONArray jsonArray = new JSONArray();

        for(DrinkOrder order : orders){
            String data = order.toData();
            jsonArray.put(data);
        }

        intent.putExtra("results", jsonArray.toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancel(View view){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Debug", "DrinkMenuActivity OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug", "DrinkMenuActivity OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Debug", "DrinkMenuActivity OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Debug", "DrinkMenuActivity OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Debug", "DrinkMenuActivity OnDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Debug", "DrinkMenuActivity OnRestart");
    }

    @Override
    public void onDrinkOrderFinish(DrinkOrder drinkOrder) {
        Boolean flag = false;
        for(int i = 0; i < orders.size(); i++){
            if(orders.get(i).drink.getDrinkNmae().equals(drinkOrder.drink.getDrinkNmae())){
                orders.set(i, drinkOrder);
                flag = true;
                break;
            }
        }
        if(!flag){
            orders.add(drinkOrder);
        }

        updateTotal();
        orders.add(drinkOrder);
    }
}
