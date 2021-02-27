package com.example.rrszoo;

import android.os.AsyncTask;
import android.view.View;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.SortedMap;

public class MessegeSender extends AsyncTask<String,Void,Void> {
    Socket socket;
    DataOutputStream dataOutputStream;
    PrintWriter printWriter;

    @Override
    protected Void doInBackground(String... voids) {
        String message =voids[0];
        try {
            socket = new Socket("192.168.1.135",20000);
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write(message);
            printWriter.flush();
            printWriter.close();
            socket.close();
/*            BufferedReader echoes = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            String response;

            do {

                printWriter.println(message);


                //Close the connection AND Thread
                if(!message.equals("exit")) {
                    response = echoes.readLine();

                }
            } while(!message.equals("exit"));
*/


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

