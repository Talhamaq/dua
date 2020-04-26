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
        int numCategory = intent.getIntExtra("name",-1);
        list = findViewById(R.id.list);
        String name = null;
        String arabic = null;
        String arabish = null;
        String translation = null;
        String reference = null;
        String count = null;
        String category= null;
        int counter = 0;
        if(numCategory==2130903043)
            category="morning_evening";
        else if(numCategory==2130903040)
            category="Daily";
        else if(numCategory==2130903045)
            category="special";
        else if(numCategory==2130903041)
            category="Dhikr";
        else if(numCategory==2130903044)
            category="Prophets";
        else if(numCategory==2130903042)
            category="Hard_times";
        final ArrayList<Dua> duas = new ArrayList<>();
        String[] starry = getResources().getStringArray(numCategory);
        int duaNum=0;
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
                    duas.add(new Dua(name, arabic, arabish, translation, reference, count,category,Integer.toString(duaNum)));
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
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("list", duas);
                intent.putExtras(bundle);
                intent.putExtra("object", duas.get(position));
                startActivity(intent);
    
    
            }
            
        });
        // public void onClicked(View view) {
        //   startActivity(new Intent(MEList.this, duaPage.class));

//    }
    }
}
