package com.example.astroweather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Settings_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_view);
        EditText szerokosc;
        EditText dlugosc;
        EditText czas;
        Switch switc = findViewById(R.id.switch1);
        Button find = findViewById(R.id.findCity);
        Model model=new ViewModelProvider(this).get(Model.class);;
        model.setMainContext(this);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Button button2;
//        String str;
//        str = findViewById(R.id.editTextTime).toString();
//        int czas = Integer.parseInt(str);
        //SettingsFragment settingsFragment = new SettingsFragment();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        szerokosc = findViewById(R.id.szerokosc_t);
        dlugosc = findViewById(R.id.dlugosc_t);
        czas = findViewById(R.id.miasto_t);
        Button button = findViewById(R.id.zat_but);
        button2 = findViewById(R.id.fav);
        if(sharedPreferences.getString("units","metric").equals("imperial")) switc.setChecked(true);
        switc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switc.isChecked()){
                    editor.putString("units","imperial");
                    editor.apply();
                }else{
                    editor.putString("units","metric");
                    editor.apply();
                }
            }
        });
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!dlugosc.getText().toString().isEmpty() && !szerokosc.getText().toString().isEmpty()){

                        String url2 = "https://api.openweathermap.org/geo/1.0/reverse" + "?lat=" + szerokosc.getText().toString()  + "&lon=" + dlugosc.getText().toString() + "&limit=2&appid=" + Model.api_key;

                        JsonArrayRequest json = new JsonArrayRequest(url2,response -> {
                            try {
                                System.out.println(response);
                                JSONObject ob = response.getJSONObject(1);
                                //city.postValue(ob.getString("name"));
                               // editor.putString("city",city.getValue());
                                //editor.apply();
                                czas.setText(ob.getString("name"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        },error -> System.out.println("errorCoor"));
                        requestQueue.add(json);
                    }
                }
            });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!czas.getText().toString().isEmpty()) {
                    int stra = 0;
                    if( sharedPreferences.getString("favourite",null)==null){
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(czas.getText().toString());
                        editor.putString("favourite",arrayList.toString());
                        editor.apply();
                    }
                    else{
                        List<FavouriteCity> list2 = getAllCity();
                        //String [] str = sharedPreferences.getString("favourite",null).replace("[","").replace("]","").split(", ");
                        ArrayList<String> arrayList = new ArrayList<>();
                        if(list2.size() > 0){
                            for(FavouriteCity elm: list2){
                                arrayList.add(elm.getName());
                            }
                        }
                    for(String a: arrayList){
                        if(a.equals(czas.getText().toString())){
                            String t = a;
                            arrayList.remove(t);
                            stra =1;
                            //DeleteFromDb(czas.getText().toString());
                            break;
                        }
                    }

                    if(stra==0){
                        AddToDb(czas.getText().toString());
                        arrayList.add(czas.getText().toString());
                    }
                    editor.putString("favourite",arrayList.toString());
                    editor.apply();


                }}
                }

        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double test;
                if(!dlugosc.getText().toString().isEmpty()) {
                    test = Double.parseDouble(dlugosc.getText().toString());
                    if (test > -180 && test < 180) {
                        Model.dlugosc1 = test;
                        editor.putString("longitude", test.toString());
                        editor.putBoolean("isCity", false);
                        editor.apply();
                    }
                }
                if(!szerokosc.getText().toString().isEmpty()) {
                    test = Double.parseDouble(szerokosc.getText().toString());
                    if (test > -90 && test < 90) {
                        Model.szerokosc1 = test;
                        editor.putString("latitude", test.toString());
                        editor.putBoolean("isCity", false);
                        editor.apply();
                    }
                }
                String test1 = "";
                if(!czas.getText().toString().isEmpty()) {
                    test1 = czas.getText().toString();
                    editor.putString("city", test1.toString());
                    editor.putBoolean("isCity",true);
                    editor.apply();

                }
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
            }
        });

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
    private void AddToDb(String name){
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        FavouriteCity fc = new FavouriteCity();
        fc.setName(name);
        db.userDao().addCity(fc);
        //finish();
    }
    private void DeleteFromDb(String name){
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        FavouriteCity fc = new FavouriteCity();
        fc.setName(name);
        db.userDao().delete(fc);
        //finish();
    }

}