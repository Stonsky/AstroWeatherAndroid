package com.example.astroweather;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodayWeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodayWeatherFragment extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TodayWeatherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodayWeatherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodayWeatherFragment newInstance(String param1, String param2) {
        TodayWeatherFragment fragment = new TodayWeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        editor = sharedPreferences.edit();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_today_weather, container, false);
        Model mdel1 = new ViewModelProvider(requireActivity()).get(Model.class);
        TextView temp = root.findViewById(R.id.temperature);
        TextView temp1 = root.findViewById(R.id.pressure);
        TextView temp2 = root.findViewById(R.id.humidity);
        TextView temp3= root.findViewById(R.id.windS);
        TextView temp4 = root.findViewById(R.id.windD);
        TextView temp5 = root.findViewById(R.id.visibility);
        TextView temp6 = root.findViewById(R.id.coordinates1);
        TextView temp62 = root.findViewById(R.id.coordinates2);
        TextView temp7 = root.findViewById(R.id.city3);
        mdel1.getCity().observe(getViewLifecycleOwner(),temp7::setText);
        //temp7.setText(mdel1.getStaticCity());
        //temp6.setText( String.valueOf(mdel1.getLocation().getLatitude()) +" " + String.valueOf( mdel1.getLocation().getLongitude()));
        mdel1.getLatitude().observe(getViewLifecycleOwner(),temp6::setText);
        mdel1.getLongitude().observe(getViewLifecycleOwner(),temp62::setText);
        mdel1.getTemp().observe(getViewLifecycleOwner(),temp::setText);
        mdel1.getPres().observe(getViewLifecycleOwner(),temp1::setText);
        mdel1.getHum().observe(getViewLifecycleOwner(),temp2::setText);
        mdel1.getWindP().observe(getViewLifecycleOwner(),temp3::setText);
        mdel1.getWindD().observe(getViewLifecycleOwner(),temp4::setText);
        mdel1.getVis().observe(getViewLifecycleOwner(),temp5::setText);
        ImageView im = root.findViewById(R.id.imageView20);
        mdel1.getIcon().observe(getViewLifecycleOwner(), e ->{
            im.setImageResource(mdel1.getDrawableByName(requireContext(),e));
        });
        return root;
    }
}