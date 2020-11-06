package com.example.dua;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences mPrefs = getSharedPreferences("IDvalue", 0);
        ArrayList<Dua> allDuas = new ArrayList<>();
        Gson gson = new Gson();
        String json = mPrefs.getString("myDuas", "");
        allDuas=gson.fromJson(json, ArrayList.class);
        if(allDuas==null) {
            initalizeDuas();
            allDuas=gson.fromJson(json, ArrayList.class);
        }
    }
    
    public void onClicked(View view) {
        Intent intent=new Intent(MainActivity.this, MEList.class);
        if (view.getId() == R.id.evening) {
            intent.putExtra("name", "morning_evening");
            startActivity(intent);
        }
        if (view.getId() == R.id.Daily) {
            intent.putExtra("name", "Daily");
            startActivity(new Intent(MainActivity.this, SubCategory.class));        }
        if (view.getId() == R.id.Special) {
            intent.putExtra("name", "special");
            startActivity(intent);        }
        if (view.getId() == R.id.dhikr) {
            intent.putExtra("name", "dhikr");
            startActivity(intent);        }
        if (view.getId() == R.id.prophet) {
            intent.putExtra("name", "favorites");
            startActivity(intent);        }
        if (view.getId() == R.id.hard_times) {
            intent.putExtra("name", "Hard_times");
            startActivity(intent);        }
    }
    public void initalizeDuas() {
        ArrayList<Dua> duas = new ArrayList<>();
        String name = null;
        String arabic = null;
        String arabish = null;
        String translation = null;
        String reference = null;
        String count = null;
        String category = null;
        int counter = 0;
        int numCategory=2130903040;
        while (numCategory<2130903046) {
            String[] starry = getResources().getStringArray(numCategory);
            if(numCategory==2130903043)
                category="morning_evening";
            else if(numCategory==2130903040)
                category="Daily";
            else if(numCategory==2130903045)
                category="special";
            else if(numCategory==2130903041)
                category="Dhikr";
            else if(numCategory==2130903044)
                category = "Prophets";
            else if(numCategory==2130903042)
                category="Hard_times";
            int duaNum = 0;
            for (String s : starry) {
                //System.out.println(s);
                switch (counter) {
                    case 0:
                        name = s;
                        counter++;
                        break;
                    case 1:
                        arabic = s;
                        counter++;
                        break;
                    case 2:
                        arabish = s;
                        counter++;
                        break;
                    case 3:
                        translation = s;
                        counter++;
                        break;
                    case 4:
                        reference = s;
                        counter++;
                        break;
                    case 5:
                        // System.out.println(s);
                        count = s;
                        counter++;
                        break;
            
                    default:
                        counter = 0;
                        duaNum++;
                        duas.add(new Dua(name, arabic, arabish, translation, reference, count, category, Integer.toString(duaNum), "false"));
                        break;
                }
            }
            numCategory++;
            
        }
        SharedPreferences  mPrefs = getSharedPreferences("IDvalue", 0);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(duas);
        prefsEditor.putString("myDuas", json);
        prefsEditor.commit();
    }
}

