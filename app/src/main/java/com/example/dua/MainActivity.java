package com.example.dua;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //NEED TO UPDATE THIS VALUE AT EVERY ADDITION TO DUAS
    String valueAppUpdaterStatic = "1.01";
    String valueAppUpdaterPersist = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences mPrefs = getSharedPreferences("IDvalue", 0);
        SharedPreferences sp = getSharedPreferences("your_prefs", 0);
        valueAppUpdaterPersist = sp.getString("valueUpdater", "");
        ArrayList<Dua> allDuas = new ArrayList<>();
        Gson gson = new Gson();
        String json = mPrefs.getString("myDuas", "");
        allDuas=gson.fromJson(json, ArrayList.class);
        if(allDuas==null || !valueAppUpdaterStatic.equals(valueAppUpdaterPersist)) {
            setUpdater();
            initalizeDuas();
            allDuas=gson.fromJson(json, ArrayList.class);
        }
    }
    public void setUpdater(){
        SharedPreferences sp = getSharedPreferences("your_prefs", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("valueUpdater", valueAppUpdaterStatic);
        editor.commit();
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
        if (view.getId() == R.id.afterPrayer) {
            intent.putExtra("name", "after_prayer");
            startActivity(intent);        }
        if (view.getId() == R.id.prophet) {
            intent.putExtra("name", "prophet");
            startActivity(intent);        }
        if (view.getId() == R.id.hard_times) {
            intent.putExtra("name", "favorites");
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
        String subCategory =null;
        
        int counter = 0;
        int numCategory=2130903040;
        int loop =0;
        while (loop<7) {
            String[] starry = getResources().getStringArray(numCategory);
            if(numCategory==2130903043)
                category="morning_evening";
            else if(numCategory==2130903041)
                category="Daily";
            else if(numCategory==2130903046)
                category="special";
            else if(numCategory==2130903040)
                category="after_prayer";
            else if(numCategory==2130903044)
                category = "Prophets";
            else if(numCategory==2130903042)
                category="Hard_times";
            int duaNum = 0;
            counter=0;
            if(!category.equals("Daily")) {
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
                            duas.add(new Dua(name, arabic, arabish, translation, reference, count, category, Integer.toString(duaNum), "false", "none"));
                            break;
                    }
                }
            }
            else {
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
                            subCategory = s;
                            counter++;
                            break;
            
                        default:
                            counter = 0;
                            duaNum++;
                            duas.add(new Dua(name, arabic, arabish, translation, reference, "1", category, Integer.toString(duaNum), "false", subCategory));
                            break;
                    }
                }
            }
            loop++;
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

