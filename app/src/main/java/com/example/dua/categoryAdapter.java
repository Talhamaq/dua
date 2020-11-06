package com.example.dua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class categoryAdapter extends
        RecyclerView.Adapter<categoryAdapter.ViewHolder> {
    private static Context t;
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
   //categoryAdapter t =this;
    public String subCategory;
    public String subCategoryParent;
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView categoryName;
        public TextView numDuas;
        public ImageView imageView;
       
        
        
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            itemView.setOnClickListener(this);
    
            categoryName = itemView.findViewById(R.id.title);
            numDuas =itemView.findViewById(R.id.subtitle);
            imageView =  itemView.findViewById(R.id.icon);
        }
    
        @Override
        public void onClick(View v) {
        System.out.println("TEST");
            Intent intent=(new Intent(categoryAdapter.t, MEList.class));
           // Bundle bundle = new Bundle();
            //bundle.putParcelableArrayList("list", duaForCategory);
            //intent.putExtras(bundle);
            intent.putExtra("subCategory", subCategory);
            intent.putExtra("subCategoryParent", subCategoryParent);
            t.startActivity(intent);
        }
    }
    private ArrayList<categoryModel> list;
    
    public categoryAdapter(ArrayList<categoryModel> list) {
        this.list = list;
    }
    @Override
    public categoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        t=context;
        LayoutInflater inflater = LayoutInflater.from(context);
        
        // Inflate the custom layout
        View subCategoryView = inflater.inflate(R.layout.categorylist, parent, false);
        
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(subCategoryView);
        return viewHolder;
    }
    
    @Override
    public void onBindViewHolder(categoryAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        categoryModel sub = list.get(position);
        
        // Set item views based on your views and data model
        subCategory=sub.getSubCategory();
        subCategoryParent=sub.getParentCategory();
        TextView categoryName = holder.categoryName;
        categoryName.setText(sub.getSubCategory());
        TextView numDuas = holder.numDuas;
        numDuas.setText("Contains "+ sub.getNumDuas()+" duas");
        ImageView imageView = holder.imageView;
        if(sub.getParentCategory().equals("morning_evening"))
            imageView.setImageResource(R.mipmap.sun);
        else if(sub.getParentCategory().equals("Daily"))
            imageView.setImageResource(R.mipmap.daily);
        else if(sub.getParentCategory().equals("special"))
            imageView.setImageResource(R.mipmap.rain);
        else if(sub.getParentCategory().equals("Dhikr"))
            imageView.setImageResource(R.mipmap.kaaba);
        else if(sub.getParentCategory().equals("Prophets"))
            imageView.setImageResource(R.mipmap.hands);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}