package com.example.astroweather;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ChangeFragment extends FragmentStateAdapter {

    public ChangeFragment(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ChangeFragment(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ChangeFragment(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TodayWeatherFragment();
            case 1:
                return new WeekWeatherFragment();
            case 2:
                return new SunFragment();
            case 3:
                return new MoonFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4 ;
    }
}
