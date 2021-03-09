package com.example.rrszoo;

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
    private static Socket s;
    private static BufferedReader bufferedReader;
    private static InputStreamReader inputStreamReader;
    private static PrintWriter printWriter;
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

            s = new Socket(ip,20000); //connect to server at port 20000

            printWriter = new PrintWriter(s.getOutputStream()); //set the output stream
            Gson gson = new Gson();
            String serializedLogIn = gson.toJson(login);
            printWriter.write(serializedLogIn); // send the message through the socket

            printWriter.flush();
            printWriter.close();
            login.clear();


            Log.d(TAG, "myTask: is" + message);

//            inputStreamReader = new InputStreamReader(s.getInputStream()); //to receive the data
//            bufferedReader = new BufferedReader(inputStreamReader);
//            message = bufferedReader.readLine();


            s.close();



        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
