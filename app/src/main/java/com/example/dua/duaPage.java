package com.example.dua;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ScrollView;

import java.util.ArrayList;

public class duaPage extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dua_page);
        Intent intent=getIntent();
        Dua dua=intent.getParcelableExtra("object");
        Bundle bundle = getIntent().getExtras();
        final ArrayList<Dua> duas= bundle.getParcelableArrayList("list");
        final int index= duas.indexOf(dua);
        
        EditText arabic= findViewById(R.id.editText);
        EditText arabish= findViewById(R.id.editText2);
        EditText translation= findViewById(R.id.editText3);
        EditText reference= findViewById(R.id.editText4);
        arabic.setText(dua.getArabic());
        arabish.setText(dua.getArabish());
        translation.setText(dua.getTranslation());
        reference.setText(dua.getReference());
    
        ScrollView background =findViewById(R.id.screen);
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
    
}
