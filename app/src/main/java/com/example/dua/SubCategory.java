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
    //private categoryModel daily[]={new categoryModel("test", 5, "Daily"), new categoryModel("test2",2,"Daily")};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setPage();
    }
    
    public void backButton(View view) {
        finish();
        
    }
    @Override
    public void onResume() {
        super.onResume();
        setPage();
    }
    public void setPage(){
        setContentView(R.layout.activity_sub_category);
        recyclerView = findViewById(R.id.subCategory);
    
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
    
        String[] starry = getResources().getStringArray(R.array.s_c_Array);
        ArrayList<categoryModel> subCategories = new ArrayList<>();
        int counter=0;
        String parentCategory="";
        String subCategory="";
        int numDuas=0;
        for (String s : starry) {
            switch (counter) {
            
                case 0:
                    parentCategory = s;
                    counter+=1;
                    break;
                case 1:
                    subCategory=s;
                    counter+=1;
                    break;
                case 2:
                    numDuas= Integer.parseInt(s);
                    counter+=1;
                    break;
                default:
                    counter=0;
                    subCategories.add(new categoryModel(subCategory, numDuas, parentCategory));
                    break;
            }
        }
    
        finalList= subCategories;
        // use a linear layout manager
    
        categoryAdapter adapter =new categoryAdapter(finalList);
        // specify an adapter (see also next example)
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    
    }
}