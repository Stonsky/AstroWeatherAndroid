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
 * Use the {@link SunFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SunFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SunFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sunFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SunFragment newInstance(String param1, String param2) {
        SunFragment fragment = new SunFragment();
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


        View root = inflater.inflate(R.layout.fragment_sun, container, false);
        Model model = new ViewModelProvider(requireActivity()).get(Model.class);
        TextView sun_wschod = root.findViewById(R.id.sun_wschod_czas);
        TextView sun_wschodA = root.findViewById(R.id.sun_wschod_azymut);
        TextView sun_zachod = root.findViewById(R.id.sun_zachod_czas);
        TextView sun_zachodA = root.findViewById(R.id.sun_zachod_azymut);
        TextView sun_dawn = root.findViewById(R.id.sun_swit);
        TextView sun_d = root.findViewById(R.id.sun_zmierzch);
        TextView wsporzedne = root.findViewById(R.id.wspolrzedne_text);
        model.getLatitude().observe(getViewLifecycleOwner(),wsporzedne::setText);

        TextView wsporzedne2 = root.findViewById(R.id.wspolrzedne_text2);
        model.getLongitude().observe(getViewLifecycleOwner(),wsporzedne2::setText);
        wsporzedne.setText( String.valueOf(model.getLocation().getLatitude()) +" " + String.valueOf( model.getLocation().getLongitude()));
        model.getSunRiseT().observe(getViewLifecycleOwner(), sun_wschod::setText);
        model.getSunRiseA().observe(getViewLifecycleOwner(), sun_wschodA::setText);
        model.getSunSetT().observe(getViewLifecycleOwner(), sun_zachod::setText);
        model.getSunSetA().observe(getViewLifecycleOwner(), sun_zachodA::setText);
        model.getDawn().observe(getViewLifecycleOwner(), sun_dawn::setText);
        model.getDusk().observe(getViewLifecycleOwner(), sun_d::setText);
        return root;
    }
}