package com.example.dua;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MEList extends AppCompatActivity {
    ListView list;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melist);
        Intent intent = getIntent();
        int category = intent.getIntExtra("name",-1);
        list = findViewById(R.id.list);
        String name = null;
        String arabic = null;
        String arabish = null;
        String translation = null;
        String reference = null;
        String count = null;
        int counter = 0;
        final ArrayList<Dua> duas = new ArrayList<>();
        String[] starry = getResources().getStringArray(category);
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
                    duas.add(new Dua(name, arabic, arabish, translation, reference, count));
                    break;
            }
            
        }
       // System.out.println(duas.size());
        CustomAdapter customAdapter = new CustomAdapter(this, duas);
        list.setAdapter(customAdapter);
        
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    
                Intent intent=(new Intent(MEList.this, duaPage.class));
                intent.putExtra("object", duas.get(position));
                startActivity(intent);
    
    
            }
            
        });
        // public void onClicked(View view) {
        //   startActivity(new Intent(MEList.this, duaPage.class));

//    }
    }
}
