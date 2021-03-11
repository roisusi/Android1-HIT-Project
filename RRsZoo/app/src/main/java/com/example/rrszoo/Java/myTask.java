package com.example.rrszoo.Java;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


class myTask extends AsyncTask<String,Void,String>
{

    private static final String TAG = "myTask";
    private static String ip = "10.0.2.2";
    private static String okMessage="";
    private static List<String> backFromServer;
    private static List<String> login;
    private static Activity activity;

    public myTask(List<String> message,String okMessages,Activity activity) {
        this.backFromServer = new ArrayList<>();
        this.activity = activity;
        this.okMessage = okMessages;
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

            okMessage = bufferedReader.readLine();
            if (okMessage != null)
                backFromServer = gson.fromJson(okMessage,ArrayList.class);

            Log.d(TAG, "myTask: is " + backFromServer);
            strings[0] = okMessage;
            Log.d(TAG, "myTask: is strings " + strings[0]);

            pr.flush();
            pr.close();
            login.clear();
            s.close();


        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.e(TAG, "onPostExecute: pre "  + okMessage);
            if (activity instanceof MainActivity && backFromServer.isEmpty()) {
                openLoginAlert();
            }
            else {

                MainActivity activity = (MainActivity) this.activity;
                activity.postLogin(backFromServer);
            }

        super.onPostExecute(s);
    }

    public void openLoginAlert(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setMessage("You have Enter Wrong User/Password");
                alertDialogBuilder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(activity,"Try Again",Toast.LENGTH_LONG).show();
                            }
                        });
/*
        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
*/
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
