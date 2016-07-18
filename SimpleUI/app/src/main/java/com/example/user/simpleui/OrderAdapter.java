package com.example.user.simpleui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 2016/7/13.
 */
public class OrderAdapter extends BaseAdapter{

    List<order> orders;
    LayoutInflater layoutInflater;

    public OrderAdapter(Context context, List<order> orders){
        this.orders = orders;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class Holder{
        TextView drinkNameTextView;
        TextView noteTextView;
        TextView storeInfoTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_order_item, null);

            TextView noteTextView = (TextView)convertView.findViewById(R.id.noteTextView);
            TextView storeInfoTextView = (TextView)convertView.findViewById(R.id.storeTextView);
            TextView drinkTextView = (TextView)convertView.findViewById(R.id.drinkNameTextView);

            holder = new Holder();
            holder.drinkNameTextView = drinkTextView;
            holder.noteTextView = noteTextView;
            holder.storeInfoTextView = storeInfoTextView;

            convertView.setTag(holder);
        }
        else {
            holder = (Holder)convertView.getTag();
        }

        order order = orders.get(position);
        holder.noteTextView.setText(order.note);
        holder.storeInfoTextView.setText(order.storeInfo);
        holder.drinkNameTextView.setText(order.menuResult);

        return convertView;
    }
}
