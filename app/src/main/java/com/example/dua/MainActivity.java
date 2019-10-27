package com.example.dua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }
    
    public void onClicked(View view) {
        Intent intent=new Intent(MainActivity.this, MEList.class);
        if (view.getId() == R.id.evening) {
            intent.putExtra("name", R.array.m_e_Array);
            startActivity(intent);
        }
        if (view.getId() == R.id.Daily) {
            intent.putExtra("name", R.array.daily_array);
            startActivity(intent);        }
        if (view.getId() == R.id.Special) {
            intent.putExtra("name", R.array.special_array);
            startActivity(intent);        }
        if (view.getId() == R.id.dhikr) {
            intent.putExtra("name", R.array.dhikr_array);
            startActivity(intent);        }
        if (view.getId() == R.id.prophet) {
            intent.putExtra("name", R.array.prophets_array);
            startActivity(intent);        }
        if (view.getId() == R.id.hard_times) {
            intent.putExtra("name", R.array.hard_times_array);
            startActivity(intent);        }
    }
    }

