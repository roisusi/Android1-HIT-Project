package com.example.rrszoo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    String message = "";
    EditText et;
    myTask mt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.EditText01);

    }

    public void ConnectToDataBase(View view){
        message = "connect";
        mt = new myTask(message);
        mt.execute();
    }

    public void onClick(View view) {
        message = et.getText().toString();
        mt = new myTask(message);
        mt.execute();
        Toast.makeText(getApplicationContext(),"Data sent",Toast.LENGTH_LONG);
    }




}