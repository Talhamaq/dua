package com.example.dua;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.core.widget.NestedScrollView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class duaPage extends AppCompatActivity {
    String categoryForBack;
    String fav;
    Dua dua;
    ActionMenuItemView favorite;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dua_page);
        Intent intent=getIntent();
        dua=intent.getParcelableExtra("object");
        Bundle bundle = getIntent().getExtras();
        final ArrayList<Dua> duas= bundle.getParcelableArrayList("list");
        final int index= duas.indexOf(dua);
        favorite =findViewById(R.id.favorite);
        fav =dua.getFavorite();
        if(fav.equals("true"))
            favorite.setIcon(getDrawable(R.drawable.baseline_favorite_black_36));
        TextView name =findViewById(R.id.duaName);
        TextView whenToRead =findViewById(R.id.description);
        TextView arabic= findViewById(R.id.arabic);
        TextView arabish= findViewById(R.id.transliteration);
        TextView translation= findViewById(R.id.translation);
        TextView reference= findViewById(R.id.referance);
        MaterialToolbar appBar=findViewById(R.id.topAppBar);
        
       // categoryForBack =dua.getCategory();
        if(dua.getCategory().equals("morning_evening")) {
            whenToRead.setText("Recite " + dua.getCount() + " times after Fajr Salah and after Asr Salah");
            appBar.setTitle("Morning/Evening Duas");
        }
        arabic.setText(dua.getArabic());
        arabish.setText(dua.getArabish());
        translation.setText(dua.getTranslation());
        reference.setText(dua.getReference());
        name.setText(dua.getNumber() +". "+dua.getName());
        
        NestedScrollView background =findViewById(R.id.nestedScroll);
        final Intent navigate=(new Intent(duaPage.this, duaPage.class));
        background.setOnTouchListener(new OnSwipeTouchListener() {
            @Override
            public boolean onSwipeRight() {
                if(duas.get(index-1)!=null)
                    navigate.putExtra("object", duas.get(index-1));
                navigate.putExtra("list", duas);
                finish();
                overridePendingTransition( 0, 0);
                startActivity(navigate);
                overridePendingTransition( 0, 0);
    
                return true;
            }
            @Override
            public boolean onSwipeLeft() {
                if(duas.get(index+1)!=null)
                    navigate.putExtra("object", duas.get(index+1));
                navigate.putExtra("list", duas);
                finish();
                overridePendingTransition( 0, 0);
                startActivity(navigate);
                overridePendingTransition( 0, 0);
                return true;
            }
            });
    }
    
    public void backButton(View view) {
       // Intent intent=new Intent(duaPage.this, MEList.class);
          //  intent.putExtra("name", categoryForBack);
           // startActivity(intent);
        finish();
    }
    
    public void onClickFavorite(MenuItem item) throws IOException {
        SharedPreferences mPrefs = getSharedPreferences("IDvalue", 0);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        ArrayList<Dua> allDuas = new ArrayList<>();
        String json = mPrefs.getString("myDuas", "");
        Type typeMyType = new TypeToken<ArrayList<Dua>>(){}.getType();
        allDuas=gson.fromJson(json, typeMyType);
        if(dua.getFavorite().equals("false")) {
            int index=allDuas.indexOf(dua);
            allDuas.get(index).setFavorite("true");
            String jsonSet = gson.toJson(allDuas);
            prefsEditor.putString("myDuas", jsonSet);
            prefsEditor.commit();
            item.setIcon(R.drawable.baseline_favorite_black_36);
            dua.setFavorite("true");
           
        }
        else{
            item.setIcon(R.drawable.baseline_favorite_border_black_36);
            allDuas.get(allDuas.indexOf(dua)).setFavorite("false");
            String jsonSet = gson.toJson(allDuas);
            prefsEditor.putString("myDuas", jsonSet);
            prefsEditor.commit();
            dua.setFavorite("false");
            
        }
    
       
    }
}
