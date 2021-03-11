package com.example.rrszoo.Java;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.PortUnreachableException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


class myTask extends AsyncTask<String,Void,String>
{

    private static final String TAG = "myTask";
    private static String ip = "10.0.2.2";
    String message = "";
    List<String> login;

    public myTask(List<String> message) {
        login = new ArrayList<>();
        this.login = message;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{

            Socket s = new Socket(ip,20000); //connect to server at port 20000
            PrintWriter pr = new PrintWriter(s.getOutputStream(), true); //set the output stream

            Gson gson = new Gson();
            String serializedLogIn = gson.toJson(login);
            pr.println(serializedLogIn);

            InputStreamReader inputStreamReader = new InputStreamReader(s.getInputStream()); //to receive the data
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String message = bufferedReader.readLine();
            Log.d(TAG, "myTask: is" + message);


            pr.flush();
            pr.close();
            login.clear();
            s.close();



        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
