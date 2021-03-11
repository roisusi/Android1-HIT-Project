package ZooServer;

import java.sql.*;


public class DataBase {

    private Connection con;

    public Login getUserName(String user , String pass) throws SQLException {
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
