package com.example.rrszoo.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.rrszoo.Java.MainPage;
import com.example.rrszoo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAddAnimal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddAnimal extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    boolean isEnglish = true;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner spinnerTypes;

    public FragmentAddAnimal() {
        // Required empty public constructor
    }

    public FragmentAddAnimal(Boolean isEnglish) {
        this.isEnglish = isEnglish;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAddAnimal.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAddAnimal newInstance(String param1, String param2) {
        FragmentAddAnimal fragment = new FragmentAddAnimal();
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

        //Hide the Menu Bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(isEnglish ? R.layout.fragment_add_animal : R.layout.fragment_add_animal_heb, container, false);

        spinnerTypes = v.findViewById(R.id.spinnerType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),R.array.Type , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerTypes.setAdapter(adapter);
        //spinnerTypes.setOnItemSelectedListener(this);

        return v;
        //return inflater.inflate(R.layout.fragment_add_animal, container, false);
    }

    public Spinner getSpinner(){
        return spinnerTypes;
    }

}