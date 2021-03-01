package com.example.rrszoo;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class myTask extends AsyncTask<Void,Void,Void>
{

    private static Socket s;
    private static PrintWriter printWriter;
    private static String ip = "10.0.2.2";
    String message = "";

    public myTask(String message) {
        this.message = message;
    }

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