package com.example.dua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class duaPage extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dua_page);
        Intent intent=getIntent();
        Dua dua=intent.getParcelableExtra("object");
        EditText arabic= findViewById(R.id.editText);
        EditText arabish= findViewById(R.id.editText2);
        EditText translation= findViewById(R.id.editText3);
        EditText reference= findViewById(R.id.editText4);
        arabic.setText(dua.getArabic());
        arabish.setText(dua.getArabish());
        translation.setText(dua.getTranslation());
        reference.setText(dua.getReference());
        
    }
}
