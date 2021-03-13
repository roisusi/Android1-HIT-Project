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
    private List<String> messageToServer;
    private List<String> stringFromServer;
    private GetInformation getInformation;
    private SendInformation sendInformation;

    private Intent intent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        title = (ImageView) findViewById(R.id.titleBar);
        messageToServer = new ArrayList<>();


        getSupportActionBar().hide();

    }

    public void registerUser(View view) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentReg, new FragmentRegister()).addToBackStack(null).commit();

//        Button backLog = (Button) findViewById(R.id.backFragReg);
//        backLog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                backToMain();
//            }
//        });

        login.setVisibility(View.INVISIBLE);
        register.setVisibility(View.INVISIBLE);
        title.setVisibility(View.INVISIBLE);
    }


    public void loginFrag(View view) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentLog, new FragmentLogin()).addToBackStack(null).commit();

//        Button backReg = (Button) findViewById(R.id.backFragLogin);
//        backReg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                backToMain();
//            }
//        });

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

    public void loginToServer(View view) {
        messageToServer.clear();
        messageToServer.add("Login");
        EditText login = (EditText) findViewById(R.id.loginText);
        messageToServer.add(login.getText().toString());
        EditText pass = (EditText) findViewById(R.id.passText);
        messageToServer.add(pass.getText().toString());
        getInformation = new GetInformation(messageToServer, MainActivity.this);
        getInformation.execute();
    }

    public void register(View view) {
        messageToServer.clear();
        EditText login = (EditText) findViewById(R.id.loginReg);
        EditText pass = (EditText) findViewById(R.id.passReg);
        EditText email = (EditText) findViewById(R.id.emailReg);

        messageToServer.add("Register");
        messageToServer.add(login.getText().toString());
        messageToServer.add(pass.getText().toString());
        messageToServer.add("false");
        messageToServer.add(email.getText().toString());
        sendInformation = new SendInformation(messageToServer, MainActivity.this,view);
        sendInformation.execute();

    }

    public void postLogin(List<String> s) {
        stringFromServer = s;
        Log.e(TAG, "test: " + stringFromServer);

        //Login login = new Login(okMessage);
        if (stringFromServer != null) {
            intent = new Intent(getApplicationContext(), MainPage.class);
            intent.putExtra("Admin", stringFromServer.get(2));
            startActivity(intent);
        }
    }

}


