package com.example.astroweather;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Handler extends FragmentActivity {

    ViewPager2 viewPager2;
    FragmentStateAdapter fragmentStateAdapter;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_handler);
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//
//        if(getResources().getBoolean(R.bool.phone) && getResources().getBoolean(R.bool.orientation)) {
//            viewPager2 = findViewById(R.id.viewPager2);
//            fragmentStateAdapter = new ChangeFragment(this);
//            viewPager2.setAdapter(fragmentStateAdapter);
//        }
//    }

//    @Override
//    public boolean onMenuItemSelected(int featureId, @NonNull MenuItem item) {
//        if(item.getItemId()==R.id.action_settings){
//            Intent intent1 = new Intent(getApplicationContext(), Settings_view.class);
//            startActivity(intent1);
//        }
//        return super.onMenuItemSelected(featureId, item);
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }


}