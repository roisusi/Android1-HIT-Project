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
    private static Animal animal;
    private static Register register;
    private static AddAnimal addAnimal;
    private static ArrayList<String> listOfTime;
    private static ArrayList<String> tempArray;


    public static void main(String[] args) throws IOException {
        db = new DataBase();
        mgs = new ArrayList<>();
            while (true) {
                try {
                    Gson gson = new Gson();
                ServerSocket ss = new ServerSocket(20000);
                Socket socket = ss.accept();

                InputStreamReader isr = new InputStreamReader(socket.getInputStream()); //to receive the data
                BufferedReader br = new BufferedReader(isr);

                message = br.readLine();
                mgs = gson.fromJson(message, ArrayList.class);

                switch (mgs.get(1)) {
                    case "Login":
                        try {
                            db.connect();
                            login = db.loginPage(mgs.get(2),mgs.get(3));
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
                            break;
                        case "Animal":
                        try {
                            db.connect();
                            if(mgs.get(0).equals("He")){
                                animal = db.animalPageHeb(mgs.get(2));
                            }
                            else {
                                animal = db.animalPage(mgs.get(2));
                            }

                            output = new PrintWriter(socket.getOutputStream(), true);
                            System.out.println("Animal is : " + animal.send() );
                            String serializedLogIn = gson.toJson(animal.send());
                            output.println(serializedLogIn);
                            output.flush();
                        } catch (SQLException throwable) {
                            throwable.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                            break;
                    case "Type":
                        try {
                            db.connect();
                            if(mgs.get(0).equals("He")){
                                listOfTime = db.getTypesToSpinnerHeb(mgs.get(2));
                            }
                            else {
                                listOfTime = db.getTypesToSpinner(mgs.get(2));
                            }
                            output = new PrintWriter(socket.getOutputStream(), true);
                            System.out.println("Animal is : " + listOfTime );
                            String serializedLogIn = gson.toJson(listOfTime);
                            output.println(serializedLogIn);
                            output.flush();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                            break;
                    case "Register":
                        try {
                            db.connect();
                            tempArray = new ArrayList<>();
                            register = new Register(mgs.get(2),mgs.get(3),mgs.get(4),mgs.get(5));
                            tempArray.add(db.register(register));
                            output = new PrintWriter(socket.getOutputStream(), true);
                            System.out.println("Detail of register is : " + mgs );
                            System.out.println("is OK ? : " + tempArray );
                            String serializedLogIn = gson.toJson(tempArray);
                            output.println(serializedLogIn);
                            output.flush();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "AddAnimal":
                        try {
                            db.connect();
                            tempArray = new ArrayList<>();
                            addAnimal = new AddAnimal(mgs.get(2),mgs.get(3),mgs.get(4),mgs.get(5),mgs.get(6),mgs.get(7),mgs.get(8));
                            tempArray.add(db.addAnimal(addAnimal));
                            output = new PrintWriter(socket.getOutputStream(), true);
                            System.out.println("Detail of Animal is : " + mgs );
                            System.out.println("is OK ? : " + tempArray );
                            String serializedLogIn = gson.toJson(tempArray);
                            output.println(serializedLogIn);
                            output.flush();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Account":
                        try {
                            System.out.println("Account User is : " + login.getLogin() + " and Password is : " + login.getPas()
                                    + " and email is : " + login.getEmail());
                            output = new PrintWriter(socket.getOutputStream(), true);
                            String serializedLogIn = gson.toJson(login.send());
                            output.println(serializedLogIn);
                            output.flush();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }


                isr.close();
                br.close();
                output.close();
                ss.close();
                socket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }
}
