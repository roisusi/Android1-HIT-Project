package com.example.rrszoo;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.PortUnreachableException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class myTask extends AsyncTask<String,Void,String>
{

    private static final String TAG = "myTask";
    private static Socket s;
    private static PrintWriter printWriter;
    private static String ip = "10.0.2.2";
    String message = "";
    List<String> login;

    public myTask(List<String> message) {
        login = new ArrayList<>();
        this.login = message;
        for (int i=0;i<login.size();i++)
            Log.d(TAG, "myTask: " + login.get(i));
        this.message = login.get(0);
    }
    @Override
    protected String doInBackground(String... strings) {
        try{

            s = new Socket(ip,20000); //connect to server at port 20000


            printWriter = new PrintWriter(s.getOutputStream()); //set the output stream

            printWriter.write(login.get(0)); // send the message through the socket
            Log.d(TAG, "doInBackground: " + login.get(0));


            printWriter.write(login.get(1)); // send the message through the socket
            Log.d(TAG, "doInBackground: " + login.get(1));
            

            printWriter.flush();
            printWriter.close();

            s.close();



        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
