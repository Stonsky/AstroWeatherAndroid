package com.example.astroweather;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;


public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    FragmentStateAdapter fragmentStateAdapter;
    Model model;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = new ViewModelProvider(this).get(Model.class);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        model.setMainContext(this);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        model.setRequestQueue(requestQueue);
        try {
            model.weather();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(getResources().getBoolean(R.bool.phone) ) {
            viewPager2 = findViewById(R.id.viewPager2);
            fragmentStateAdapter = new ChangeFragment(this);
            viewPager2.setAdapter(fragmentStateAdapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_settings){
            Intent intent1 = new Intent(getApplicationContext(), Settings_view.class);
            startActivity(intent1);
        }
        if(item.getItemId()==R.id.action_favourite){
            Intent intent1 = new Intent(getApplicationContext(), Favourite.class);
            startActivity(intent1);
        }
        if(item.getItemId()==R.id.action_reload){
            try {
                model.reload();
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }


}