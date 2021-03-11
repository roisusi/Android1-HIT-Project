package ZooServer;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;


public class DataBase {

    private Connection con;

    public Login loginPage(String user , String pass) throws SQLException {
        String selectSql = "select * from Users where Users ="+ "\""  + user + "\"" + "and Password=" + pass;
        Statement selectStatment = con.createStatement();
        selectStatment.getResultSet();
        ResultSet results = null;
        try{
            results = selectStatment.executeQuery(selectSql);
        }
        catch (SQLException e){
            return null;

        }

        String name="";
        String password="";
        String Admin="";
        String Email="";
        while (results.next()) {
            name = results.getString("Users");
            password = results.getString("Password");
            Admin = results.getString("Admin");
            Email = results.getString("Email");

        }
        selectStatment.close();

        return new Login(name,password,Admin,Email);
    }

    public Animal animalPage(String animal) throws SQLException {

        String selectSql = "select * from Animals where Name ="+ "\""  + animal + "\"";
        Statement selectStatment = con.createStatement();
        selectStatment.getResultSet();
        ResultSet results = null;
        try{
            results = selectStatment.executeQuery(selectSql);
        }
        catch (SQLException e){
            return null;

        }

        String type="";
        String name="";
        String location="";
        String lifetime="";
        String food="";
        String numberOfChildrens="";
        String imageURI="";

        while (results.next()) {
            type = results.getString("Type");
            name = results.getString("Name");
            location = results.getString("Location");
            lifetime = results.getString("LifeTime");
            food = results.getString("Food");
            numberOfChildrens = results.getString("NumOfChildrens");
            imageURI = results.getString("ImageURI");

        }
        selectStatment.close();


        return new Animal(type,name,location,lifetime,food,numberOfChildrens,imageURI);
    }


    public ArrayList<String> getTypesToSpinner(String Type) throws SQLException {

        String selectSql = "select * from Animals where Type ="+ "\""  + Type + "\"";
        Statement selectStatment = con.createStatement();
        selectStatment.getResultSet();
        ResultSet results = null;
        try{
            results = selectStatment.executeQuery(selectSql);
        }
        catch (SQLException e){
            return null;

        }

        ArrayList animalTypes = new ArrayList();

        while (results.next()) {
            animalTypes.add(results.getString("Name"));
        }
        selectStatment.close();

        return animalTypes;

    }

    //                          //
    //  Connection To DataBase  //
    //                          //
    public void connect() throws Exception {
        con = DatabaseConnection.getInstance().getConnection();
    }
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException throwable) {

                System.out.println("Connection Closed");
            }
        }
    }
    public Connection getCon() {
        return con;
    }


}
