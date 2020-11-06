package com.example.dua;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class CustomAdapter extends BaseAdapter {
   private ArrayList<Dua> duas=new ArrayList<>();
    Activity context;
    public CustomAdapter(Activity context, ArrayList<Dua> arrayList) {
        this.duas=arrayList;
        this.context=context;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public int getCount() {
        return duas.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Dua dua=duas.get(position);
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.customizedlist, null,true);
    
        TextView titleText =  rowView.findViewById(R.id.title);
        ImageView imageView =  rowView.findViewById(R.id.icon);
        TextView subtitleText = rowView.findViewById(R.id.subtitle);
        String sub;
        if(dua.getCount().equals("1"))
            sub="Recite "+dua.getCount()+" time";
        else
            sub="Recite "+dua.getCount()+" times";
        String name=dua.getNumber()+". "+dua.getName();
        titleText.setText(name);
        if(dua.getCategory().equals("morning_evening"))
            imageView.setImageResource(R.mipmap.sun);
        else if(dua.getCategory().equals("Daily"))
            imageView.setImageResource(R.mipmap.daily);
        else if(dua.getCategory().equals("special"))
            imageView.setImageResource(R.mipmap.rain);
        else if(dua.getCategory().equals("Dhikr"))
            imageView.setImageResource(R.mipmap.kaaba);
        else if(dua.getCategory().equals("Prophets"))
            imageView.setImageResource(R.mipmap.hands);
        else if(dua.getCategory().equals("Hard_times"))
            imageView.setImageResource(R.mipmap.hard);
        subtitleText.setText(sub);
    
        return rowView;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return duas.size();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
}
