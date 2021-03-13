package com.example.rrszoo.Fragments;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rrszoo.Java.MainPage;
import com.example.rrszoo.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLogin extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private static boolean logFlag=true;
    private EditText loginText;
    private EditText passText;
    private List<String> stringFromServer;


    public FragmentLogin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLogin.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLogin newInstance(String param1, String param2) {
        FragmentLogin fragment = new FragmentLogin();
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
        stringFromServer = new ArrayList<>();
        this.pref = getContext().getSharedPreferences("RRsZoo", MODE_PRIVATE); // for getting and save on computer
        this.editor = this.pref.edit(); // for editing


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_login, container, false);
        loginText = (EditText) v.findViewById(R.id.loginText);
        passText = (EditText) v.findViewById(R.id.passText);

        //return inflater.inflate(R.layout.fragment_login, container, false);
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void loginFromServer(List<String> s){
        stringFromServer=s;
        setLoginDetails(stringFromServer.get(0),stringFromServer.get(1));
    }



    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if (logFlag)
            this.tryLogIn();
        else {
            //clear cache so it not remember you
            editor.putString("login", null);
            editor.putString("password", null);
            editor.apply();
        }
    }
    private void tryLogIn()  {
        final String login = this.pref.getString("login", null);
        final String password = this.pref.getString("password", null);
        if (login != null && password != null) {
            loginText.setText(login);
            passText.setText(password);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void setLoginDetails(String login, String password) {
        editor.putString("login", login);
        editor.putString("password", password);
        editor.apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void cleanLoginDetails() { // call this when logout
        setLoginDetails(null, null);
    }



}