package com.manojprabhakar.tutorial1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ManojPrabhakar on 2/3/2016.
 */
public class CustomListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    private List<Market> market;

    public CustomListAdapter(Context context, List<Market> markets){
        this.context = context;
        this.market = markets;
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return market.size();
    }

    @Override
    public Object getItem(int position) {
        return market.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(layoutInflater == null){
            layoutInflater = (LayoutInflater)context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null)
            convertView = layoutInflater.inflate(R.layout.listview_layout, null);
        TextView name = (TextView)convertView.findViewById(R.id.listname);
        TextView mrketid = (TextView)convertView.findViewById(R.id.listmarketid);
        Market mar = market.get(position);
        name.setText(String.valueOf(mar.getName()));
        mrketid.setText(String.valueOf(mar.getMarketID()));
        return convertView;
    }
}
