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
 * Use the {@link WeekWeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeekWeatherFragment extends Fragment {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeekWeatherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeekWeatherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeekWeatherFragment newInstance(String param1, String param2) {
        WeekWeatherFragment fragment = new WeekWeatherFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root2 = inflater.inflate(R.layout.fragment_week_weather, container, false);
        Model model2 = new ViewModelProvider(requireActivity()).get(Model.class);
        TextView temp1 = root2.findViewById(R.id.tmp1);
        TextView temp2 = root2.findViewById(R.id.tmp2);
        TextView temp3 = root2.findViewById(R.id.tmp3);
        TextView temp4 = root2.findViewById(R.id.tmp4);
        TextView temp5 = root2.findViewById(R.id.tmp5);
        TextView temp6 = root2.findViewById(R.id.coorWeek);
        model2.getLatitude().observe(getViewLifecycleOwner(),temp6::setText);
        TextView temp61 = root2.findViewById(R.id.coorWeek2);
        model2.getLongitude().observe(getViewLifecycleOwner(),temp61::setText);
        TextView temp7 = root2.findViewById(R.id.day1);
        TextView temp8 = root2.findViewById(R.id.day2);
        TextView temp9 = root2.findViewById(R.id.day3);
        TextView temp10 = root2.findViewById(R.id.day4);
        TextView temp11 = root2.findViewById(R.id.day5);
        TextView temp12 = root2.findViewById(R.id.cityNameW);
        ImageView im1 = root2.findViewById(R.id.imageView1);
        ImageView im2 = root2.findViewById(R.id.imageView4);
        ImageView im3 = root2.findViewById(R.id.imageView5);
        ImageView im4 = root2.findViewById(R.id.imageView6);
        ImageView im5 = root2.findViewById(R.id.imageView7);
        model2.getCity().observe(getViewLifecycleOwner(),temp12::setText);
        model2.getNext_1_Date().observe(getViewLifecycleOwner(),temp7::setText);
        model2.getNext_2_Date().observe(getViewLifecycleOwner(),temp8::setText);
        model2.getNext_3_Date().observe(getViewLifecycleOwner(),temp9::setText);
        model2.getNext_4_Date().observe(getViewLifecycleOwner(),temp10::setText);
        model2.getNext_5_Date().observe(getViewLifecycleOwner(),temp11::setText);

        model2.getNext_1_Temp().observe(getViewLifecycleOwner(),temp1::setText);
        model2.getNext_2_Temp().observe(getViewLifecycleOwner(),temp2::setText);
        model2.getNext_3_Temp().observe(getViewLifecycleOwner(),temp3::setText);
        model2.getNext_4_Temp().observe(getViewLifecycleOwner(),temp4::setText);
        model2.getNext_5_Temp().observe(getViewLifecycleOwner(),temp5::setText);
        //im1.setImageResource();
        model2.getNext_1_Icon().observe(getViewLifecycleOwner(),e->{ im1.setImageResource(model2.getDrawableByName(requireContext(),e)); });
        model2.getNext_2_Icon().observe(getViewLifecycleOwner(),e->{ im2.setImageResource(model2.getDrawableByName(requireContext(),e)); });
        model2.getNext_3_Icon().observe(getViewLifecycleOwner(),e->{ im3.setImageResource(model2.getDrawableByName(requireContext(),e)); });
        model2.getNext_4_Icon().observe(getViewLifecycleOwner(),e->{ im4.setImageResource(model2.getDrawableByName(requireContext(),e)); });
        model2.getNext_5_Icon().observe(getViewLifecycleOwner(),e->{ im5.setImageResource(model2.getDrawableByName(requireContext(),e)); });

        return root2;
    }
}