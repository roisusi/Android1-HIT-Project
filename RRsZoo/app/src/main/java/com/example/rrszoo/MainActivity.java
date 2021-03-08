package com.example.rrszoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Button login;
    private Button register;
    private Button guest;
    private ImageView title;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }




    public void loginFrag (View view){
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment,new FragmentLogin()).commit();
        login = (Button) findViewById(R.id.login);
        login.setVisibility(View.INVISIBLE);
        register = (Button) findViewById(R.id.register);
        register.setVisibility(View.INVISIBLE);
        guest = (Button) findViewById(R.id.guest);
        guest.setVisibility(View.INVISIBLE);
        title = (ImageView) findViewById(R.id.titleBar);
        title.setVisibility(View.INVISIBLE);

    }


}