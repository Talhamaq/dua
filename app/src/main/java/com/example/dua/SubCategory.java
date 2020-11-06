package com.example.dua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Arrays;

public class SubCategory extends AppCompatActivity {
    private RecyclerView recyclerView;
    
    private ArrayList<categoryModel> finalList;
    private categoryModel daily[]={new categoryModel("test", 5, "morning_evening"), new categoryModel("test2",2,"morning_evening")};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        recyclerView = findViewById(R.id.subCategory);
    
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
    
        finalList= new ArrayList<categoryModel>(Arrays.asList(daily));;
        // use a linear layout manager
        
        categoryAdapter adapter =new categoryAdapter(finalList);
        // specify an adapter (see also next example)
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
    }
}