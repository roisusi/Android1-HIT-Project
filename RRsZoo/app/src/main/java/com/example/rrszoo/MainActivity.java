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

    private static Socket s;
    private static PrintWriter printWriter;
    String message = "";
    EditText et;
    private static String ip = "192.168.1.135";
    myTask mt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.EditText01);
    }

    public void ConnectToDataBase(View view){
        message = "connect";
        mt = new myTask();
        mt.execute();
    }

    class myTask extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            try{
                s = new Socket(ip,20000); //connect to server at port 20000
                printWriter = new PrintWriter(s.getOutputStream()); //set the output stream
                printWriter.write(message); // send the message through the socket
                printWriter.flush();
                printWriter.close();
                s.close();

            }catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }
    }


    public void onClick(View view) {
        message = et.getText().toString();
        mt = new myTask();
        mt.execute();
        Toast.makeText(getApplicationContext(),"Data sent",Toast.LENGTH_LONG);
    }
}