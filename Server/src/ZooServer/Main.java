package ZooServer;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static String message = "";
    private static PrintWriter output;
    private static List<String> mgs;
    private static DataBase db;
    private static Login login;


    public static void main(String[] args) throws IOException {
        db = new DataBase();
        mgs = new ArrayList<>();
        try {
            while (true) {
                Gson gson = new Gson();
                ServerSocket ss = new ServerSocket(20000);
                Socket socket = ss.accept();

                InputStreamReader isr = new InputStreamReader(socket.getInputStream()); //to receive the data
                BufferedReader br = new BufferedReader(isr);

                message = br.readLine();
                mgs = gson.fromJson(message, ArrayList.class);

                switch (mgs.get(0)) {
                    case "Login":
                        try {
                            db.connect();
                            login = db.getUserName(mgs.get(1),mgs.get(2));
                            output = new PrintWriter(socket.getOutputStream(), true);
                            System.out.println("User is : " + login.getLogin() + " and Password is : " + login.getPas()
                            + " and email is : " + login.getEmail());
                            String serializedLogIn = gson.toJson(login.send());
                            output.println(serializedLogIn);
                            output.flush();
                        } catch (SQLException throwable) {
                            throwable.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }


                isr.close();
                br.close();
                output.close();
                ss.close();
                socket.close();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
