package com.example.astroweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Favourite extends AppCompatActivity {
    //public static Databasetest databasetest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favourite);
       // databasetest = Room.databaseBuilder(getApplicationContext() , Databasetest.class,"userdb").build();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putString("favourite","Moscow");
        //editor.apply();
        List<FavouriteCity> list2 = getAllCity();
        //String [] str = sharedPreferences.getString("favourite",null).replace("[","").replace("]","").split(", ");
        ArrayList<String> arrayList = new ArrayList<>();
        if(list2.size() > 0){
            for(FavouriteCity elm: list2){
               arrayList.add(elm.getName());
            }
        }

        ListView list = findViewById(R.id.favlist);
        ArrayAdapter a = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(a);
        list.setOnItemLongClickListener(((parent, view, position, id) -> {
            //DeleteFromDb(arrayList.get(position));
            DeleteFromDb2(arrayList.get(position));
            Intent intent = new Intent(getApplicationContext(), Favourite.class);
            startActivity(intent);
            return true;
        }));
        list.setOnItemClickListener(((parent, view, position, id) -> {
            editor.putString("city",arrayList.get(position));
            editor.putBoolean("isCity",true);
            editor.apply();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }));
    }


    private List<FavouriteCity> getAllCity(){
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<FavouriteCity> list;
        FavouriteCity fc = new FavouriteCity();
        //fc.setName(name);
        list = db.userDao().getAll();
        //finish();
        return list;
    }
    private void DeleteFromDb(String name){
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        FavouriteCity fc = new FavouriteCity();
        fc.setName(name);
        db.userDao().delete(fc);
        //finish();
    }
    private void DeleteFromDb2(String name){
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        //FavouriteCity fc = new FavouriteCity();
        //fc.setName(name);
        db.userDao().deletecity(name);
        //finish();
    }

}