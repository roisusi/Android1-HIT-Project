package com.example.rrszoo.Java;

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

import com.example.rrszoo.Fragments.FragmentLogin;
import com.example.rrszoo.Fragments.FragmentRegister;
import com.example.rrszoo.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    static final String TAG = "MainActivity";
    private FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private Button login;
    private Button register;
    private ImageView title;
    private List<String> message;
    private List<String> loginMessage;
    private String okMessage="";
    private myTask mt;
    private Intent intent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        title = (ImageView) findViewById(R.id.titleBar);
        message = new ArrayList<>();
        loginMessage = new ArrayList<>();


    }

    public void registerUser(View view){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentReg, new FragmentRegister()).addToBackStack(null).commit();

        login.setVisibility(View.INVISIBLE);
        register.setVisibility(View.INVISIBLE);
        title.setVisibility(View.INVISIBLE);
    }


    public void loginFrag(View view) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentLog, new FragmentLogin()).addToBackStack(null).commit();

        login.setVisibility(View.INVISIBLE);
        register.setVisibility(View.INVISIBLE);
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
            title.setVisibility(View.VISIBLE);
        }
    }
    public void loginToServer(View view){

//        Intent intent = new Intent(MainActivity.this, AnimalPage.class);
//        startActivity(intent);

        message.add("Login");
        EditText login = (EditText) findViewById(R.id.loginText);
        message.add(login.getText().toString());
        EditText pass = (EditText) findViewById(R.id.passText);
        message.add(pass.getText().toString());
        mt = new myTask(message,okMessage,MainActivity.this);
        mt.execute(okMessage);


    }

    public void onClick(View view) {
        //mt = new myTask(message,okMessage);
        //mt.execute();
        Toast.makeText(getApplicationContext(),"Data sent",Toast.LENGTH_LONG);
    }

    public void register(View view){
        //DataBase
    }

    public String postLogin(List<String> s){
        loginMessage = s;
        Log.e(TAG, "test: " + loginMessage );

        //Login login = new Login(okMessage);
        if(loginMessage != null) {
            intent = new Intent(getApplicationContext(), MainPage.class);
            intent.putExtra("Admin",loginMessage.get(2));
            startActivity(intent);
        }
        return okMessage;
    }

}


