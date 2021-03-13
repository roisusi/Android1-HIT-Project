package ZooServer;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DataBase {

    private Connection con;

    public Login loginPage(String user, String pass) throws SQLException {
        String selectSql = "select * from Users where Users =" + "\"" + user + "\"" + "and Password=" + pass;
        Statement selectStatment = con.createStatement();
        selectStatment.getResultSet();
        ResultSet results = null;
        try {
            results = selectStatment.executeQuery(selectSql);
        } catch (SQLException e) {
            return null;

        }

        String name = "";
        String password = "";
        String Admin = "";
        String Email = "";
        while (results.next()) {
            name = results.getString("Users");
            password = results.getString("Password");
            Admin = results.getString("Admin");
            Email = results.getString("Email");

        }
        selectStatment.close();

        return new Login(name, password, Admin, Email);
    }

    public Animal animalPage(String animal) throws SQLException {

        String selectSql = "select * from Animals where Name =" + "\"" + animal + "\"";
        Statement selectStatment = con.createStatement();
        selectStatment.getResultSet();
        ResultSet results = null;
        try {
            results = selectStatment.executeQuery(selectSql);
        } catch (SQLException e) {
            return null;

        }

        String type = "";
        String name = "";
        String location = "";
        String lifetime = "";
        String food = "";
        String numberOfChildrens = "";
        String imageURI = "";

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


        return new Animal(type, name, location, lifetime, food, numberOfChildrens, imageURI);
    }

    public Animal animalPageHeb(String animal) throws SQLException {

        String selectSql = "select * from Animals_Heb where Name =" + "\"" + animal + "\"";
        Statement selectStatment = con.createStatement();
        selectStatment.getResultSet();
        ResultSet results = null;
        try {
            results = selectStatment.executeQuery(selectSql);
        } catch (SQLException e) {
            return null;

        }

        String type = "";
        String name = "";
        String location = "";
        String lifetime = "";
        String food = "";
        String numberOfChildrens = "";
        String imageURI = "";

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


        return new Animal(type, name, location, lifetime, food, numberOfChildrens, imageURI);
    }


    public ArrayList<String> getTypesToSpinner(String Type) throws SQLException {

        String selectSql = "select * from Animals where Type =" + "\"" + Type + "\"";
        Statement selectStatment = con.createStatement();
        selectStatment.getResultSet();
        ResultSet results = null;
        try {
            results = selectStatment.executeQuery(selectSql);
        } catch (SQLException e) {
            return null;

        }

        ArrayList animalTypes = new ArrayList();

        while (results.next()) {
            animalTypes.add(results.getString("Name"));
        }
        selectStatment.close();

        return animalTypes;

    }

    public ArrayList<String> getTypesToSpinnerHeb(String Type) throws SQLException {

        String selectSql = "select * from Animals_Heb where Type =" + "\"" + Type + "\"";
        Statement selectStatment = con.createStatement();
        selectStatment.getResultSet();
        ResultSet results = null;
        try {
            results = selectStatment.executeQuery(selectSql);
        } catch (SQLException e) {
            return null;

        }

        ArrayList animalTypes = new ArrayList();

        while (results.next()) {
            animalTypes.add(results.getString("Name"));
        }
        selectStatment.close();

        return animalTypes;

    }

    //                    //
    // Insert to DataBase //
    //                    //

    public String register(Register register) throws SQLException {

        String selectSql = "select * from Users where Users =" + "\"" + register.getName() + "\"";
        Statement selectStatment = con.createStatement();
        selectStatment.getResultSet();
        ResultSet results = null;
        try {
            results = selectStatment.executeQuery(selectSql);
        } catch (SQLException e) {

        }
        String name = "";
        while (results.next()) {
            name = results.getString("Users");
        }
        selectStatment.close();


        String returnStr = "";

        if (results != null && name.isEmpty()) {

            String insertSql = "insert into Users (Users,Password,Admin,Email) values(?,?,?,?)";
            PreparedStatement insertStmt = con.prepareStatement(insertSql);
            int i = 1;

            int col = 1;
            insertStmt.setString(col++, register.getName());
            insertStmt.setString(col++, register.getPassword());
            insertStmt.setString(col++, register.getAdmin());
            insertStmt.setString(col++, register.getEmail());
            insertStmt.executeUpdate();

            insertStmt.close();
            return returnStr = "OK";
        } else {
            return returnStr = "NOT OK";
        }
    }

    public String addAnimal(AddAnimal addAnimal) throws SQLException {

        String selectSql = "select * from Animals where Type =" + "\"" + addAnimal.getType() + "\"" + " and Name=\"" + addAnimal.getName() + "\"" ;
        Statement selectStatment = con.createStatement();
        selectStatment.getResultSet();
        ResultSet results = null;
        try {
            results = selectStatment.executeQuery(selectSql);
        } catch (SQLException e) {

        }

        String name = "";
        while (results != null && results.next()) {
            name = results.getString("Name");

        }
        selectStatment.close();


        String returnStr = "";

        if (name.isEmpty()) {

            String insertSql = "insert into Animals (Type,Name,Location,LifeTime,Food,NumOfChildrens,ImageURI) values(?,?,?,?,?,?,?)";
            PreparedStatement insertStmt = con.prepareStatement(insertSql);
            int i = 1;

            int col = 1;
            insertStmt.setString(col++, addAnimal.getType());
            insertStmt.setString(col++, addAnimal.getName());
            insertStmt.setString(col++, addAnimal.getLocation());
            insertStmt.setString(col++, addAnimal.getLifetime());
            insertStmt.setString(col++, addAnimal.getFood());
            insertStmt.setString(col++, addAnimal.getNumberOfChildrens());
            insertStmt.setString(col++, addAnimal.getImage());
            insertStmt.executeUpdate();

            insertStmt.close();
            return returnStr = "OK";
        } else {
            return returnStr = "NOT OK";
        }
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
