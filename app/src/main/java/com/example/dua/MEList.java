package com.example.dua;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MEList extends AppCompatActivity {
    ListView list;
    String Category;
    String subCategory;
    ArrayList<Dua> duas;
    ArrayList<Dua> duaForCategory;
    boolean isResume=false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melist);
        Intent intent = getIntent();
        Category = intent.getStringExtra("name");
        subCategory = intent.getStringExtra("subCategory");
        list = findViewById(R.id.list);
        MaterialToolbar appBar=findViewById(R.id.topAppBar);
        switch (Category){
            case "morning_evening":
                appBar.setTitle("Morning/Evening Duas");
                break;
            case "Daily":
                appBar.setTitle("Daily Duas");
                break;
            case "special":
                appBar.setTitle("Special Duas");
                break;
            case "after_prayer":
                appBar.setTitle("Dua after Prayer");
                break;
            case "prophet":
                appBar.setTitle("Prophet Duas");
                break;
            case "favorites":
                appBar.setTitle("Favorites Duas");
                break;
    
        }
        
//        String name = null;
//        String arabic = null;
//        String arabish = null;
//        String translation = null;
//        String reference = null;
//        String count = null;
//        String category= null;
//        int counter = 0;
//        final ArrayList<Dua> duas = new ArrayList<>();
//        String[] starry = getResources().getStringArray(numCategory);
//        if(numCategory==2130903043)
//            category="morning_evening";
//        else if(numCategory==2130903040)
//            category="Daily";
//        else if(numCategory==2130903045)
//            category="special";
//        else if(numCategory==2130903041)
//            category="Dhikr";
//        else if(numCategory==2130903044) {
//            category = "Prophets";
//            try {
//                starry=favoritesSelected();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        else if(numCategory==2130903042)
//            category="Hard_times";
//
//       // final ArrayList<Dua> duas = new ArrayList<>();
//        //String[] starry = getResources().getStringArray(numCategory);
//        int duaNum=0;
//        for (String s : starry) {
//            //System.out.println(s);
//            switch (counter) {
//                case 0:
//                    name = s;
//                    counter++;
//                    break;
//                case 1:
//                    arabic = s;
//                    counter++;
//                    break;
//                case 2:
//                    arabish = s;
//                    counter++;
//                    break;
//                case 3:
//                    translation = s;
//                    counter++;
//                    break;
//                case 4:
//                    reference = s;
//                    counter++;
//                    break;
//                case 5:
//                   // System.out.println(s);
//                    count = s;
//                    counter++;
//                    break;
//
//                default:
//                    counter = 0;
//                    duaNum++;
//                    duas.add(new Dua(name, arabic, arabish, translation, reference, count,category,Integer.toString(duaNum),favoriteFile(name)));
//                    break;
//            }
            
       // }
       // System.out.println(duas.size());
//        TextView noDua=findViewById(R.id.noDua);
       SharedPreferences mPrefs = getSharedPreferences("IDvalue", 0);
      // ArrayList<Dua> duas = new ArrayList<>();
       Gson gson = new Gson();
       String json = mPrefs.getString("myDuas", "");
       Type typeMyType = new TypeToken<ArrayList<Dua>>(){}.getType();
       duas =gson.fromJson(json, typeMyType);
       duaForCategory =setCategoryArray(Category, duas);
       
        if(duaForCategory.isEmpty()){
//            noDua.setVisibility(View.VISIBLE);
        }
        else {
//            noDua.setVisibility(View.INVISIBLE);
            CustomAdapter customAdapter = new CustomAdapter(this, duaForCategory);
            list.setAdapter(customAdapter);
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    
                Intent intent=(new Intent(MEList.this, duaPage.class));
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("list", duaForCategory);
                intent.putExtras(bundle);
                intent.putExtra("object", duaForCategory.get(position));
                startActivity(intent);
    
    
            }
            
        });
        // public void onClicked(View view) {
        //   startActivity(new Intent(MEList.this, duaPage.class));

//    }
    }
    
    private ArrayList<Dua> setCategoryArray(String category, ArrayList<Dua> duas) {
        ArrayList<Dua> duaForCategory = new ArrayList<>();
        if(category.equals("Daily")) {
            for (Dua dua : duas) {
                if (dua.getCategory().equals(category))
                    if(dua.getSubCategory().equals(subCategory))
                        duaForCategory.add(dua);
            }
        }
       else if(!category.equals("favorites")) {
            for (Dua dua : duas) {
                if (dua.getCategory().equals(category))
                    duaForCategory.add(dua);
            }
        }
        else{
            for (Dua dua : duas) {
                if(dua.getFavorite().equals("true"))
                    duaForCategory.add(dua);
            }
        }
        return duaForCategory;
        
    }
    @Override
    public void onResume(){
        super.onResume();
        if(isResume) {
//            TextView noDua = findViewById(R.id.noDua);
            SharedPreferences mPrefs = getSharedPreferences("IDvalue", 0);
            // ArrayList<Dua> duas = new ArrayList<>();
            Gson gson = new Gson();
            String json = mPrefs.getString("myDuas", "");
            Type typeMyType = new TypeToken<ArrayList<Dua>>() {
            }.getType();
            duas.clear();
            duaForCategory.clear();
            duas = gson.fromJson(json, typeMyType);
            duaForCategory = setCategoryArray(Category, duas);
    
            if (duaForCategory.isEmpty()) {
//                noDua.setVisibility(View.VISIBLE);
            } else {
//                noDua.setVisibility(View.INVISIBLE);
                CustomAdapter customAdapter = new CustomAdapter(this, duaForCategory);
                list.setAdapter(customAdapter);
                customAdapter.notifyDataSetChanged();
            }
        }
        isResume=true;
    }
    
    public void backButton(View view) {
        finish();
    
    }
//    public String favoriteFile(String name){
//        try {
//            FileOutputStream fOut = openFileOutput("favorite.txt", Context.MODE_APPEND);
//            FileInputStream fin  = openFileInput("favorite.txt");
//            Scanner scanner = new Scanner(fin);
//            String selected;
//            while(scanner.hasNextLine()){
//                selected=scanner.nextLine();
//
//               if (selected.equals(name)){
//                   System.out.println("READING FAVORITE: "+selected);
//                   return "true";
//               }
//            }
//            fin.close();
//            scanner.close();
//        }catch (Exception e){
//            Log.i("MEList","Exception occurred "+e.toString());
//        }
//        return "false";
//
//    }
//    public String[] favoritesSelected() throws FileNotFoundException {
//        ArrayList<String> finalList=new ArrayList<>();
//        ArrayList<String> listOfFav=new ArrayList<>();
//        FileInputStream fin  = openFileInput("favorite.txt");
//        Scanner scanner = new Scanner(fin);
//        while(scanner.hasNextLine()){
//            listOfFav.add(scanner.nextLine());
//        }
//        int numCategory=2130903040;
//        while(numCategory<=2130903045){
//            String[] starry = getResources().getStringArray(numCategory);
//            for(int i=0; i<starry.length;i=i+7){
//                for(String item: listOfFav){
//                    if(starry[i].equals(item)){
//                        for(int j=i; j <(i+7);j++)
//                            finalList.add(starry[j]);
//                    }
//                }
//            }
//            numCategory++;
//        }
//        return  finalList.toArray(new String[finalList.size()]);
//    }
}
