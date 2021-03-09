package com.example.rrszoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    static final String TAG = "MainActivity";
    private FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private Button login;
    private Button register;
    private Button guest;
    private ImageView title;
    private List<String> message;
    myTask mt;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        guest = (Button) findViewById(R.id.guest);
        title = (ImageView) findViewById(R.id.titleBar);
        message = new ArrayList<>();
    }


    public void loginFrag(View view) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment, new FragmentLogin()).addToBackStack(null).commit();


        login.setVisibility(View.INVISIBLE);
        register.setVisibility(View.INVISIBLE);
        guest.setVisibility(View.INVISIBLE);
        title.setVisibility(View.INVISIBLE);

    }

    ///backToMain
    public void backToMain(View view) {
        Log.d(TAG, "onBackPressed: ");
        if (fragmentManager.getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            fragmentManager.popBackStack();
            login.setVisibility(View.VISIBLE);
            register.setVisibility(View.VISIBLE);
            guest.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
        }
    }
    public void ConnectToDataBase(View view){

/*        Intent intent = new Intent(MainActivity.this, AnimalPage.class);
        startActivity(intent);*/
        /*Intent intent = new Intent(MainActivity.this, MainPage.class);
        startActivity(intent);*/

        EditText login = (EditText) findViewById(R.id.loginText);
        message.add(login.getText().toString());
        EditText pass = (EditText) findViewById(R.id.passText);
        message.add(pass.getText().toString());

        mt = new myTask(message);
        mt.execute();

    }

    public void onClick(View view) {
        mt = new myTask(message);
        mt.execute();
        Toast.makeText(getApplicationContext(),"Data sent",Toast.LENGTH_LONG);
    }


}


