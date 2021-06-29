package com.example.astroweather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoonFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MoonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment moonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoonFragment newInstance(String param1, String param2) {
        MoonFragment fragment = new MoonFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root1 = inflater.inflate(R.layout.fragment_moon, container, false);
        Model model1 = new ViewModelProvider(requireActivity()).get(Model.class);
        TextView moon_dzien1 = root1.findViewById(R.id.moon_dzien);
        TextView moon_now1 = root1.findViewById(R.id.moon_now);
        TextView moon_faza1 = root1.findViewById(R.id.moon_faza);
        TextView moon_wch = root1.findViewById(R.id.moon_wschod);
        TextView moon_zach = root1.findViewById(R.id.moon_zachod);
        TextView moon_pelnia1 = root1.findViewById(R.id.moon_pelnia);
        TextView wsporzedne = root1.findViewById(R.id.wspolrzedne_text1);
        wsporzedne.setText( String.valueOf(model1.getLocation().getLatitude()) +" " + String.valueOf( model1.getLocation().getLongitude()));
        model1.getMoonFull().observe(getViewLifecycleOwner(), moon_pelnia1::setText);
        model1.getMoonDaysToInterlunar().observe(getViewLifecycleOwner(), moon_dzien1::setText);
        model1.getMoonPhase().observe(getViewLifecycleOwner(), moon_faza1::setText);
        model1.getInterlunar().observe(getViewLifecycleOwner(), moon_now1::setText);
        model1.getMoonRise().observe(getViewLifecycleOwner(), moon_wch::setText);
        model1.getMoonSet().observe(getViewLifecycleOwner(), moon_zach::setText);
        return root1;
    }
}