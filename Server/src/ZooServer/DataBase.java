package ZooServer;

import java.sql.*;


public class DataBase {

    private Connection con;

    public Login getUserName(String user , String pass) throws SQLException {
        String selectSql = "select * from Users where Users ="+ "\""  + user + "\"" + "and Password=" + pass;
        Statement selectStatment = con.createStatement();
        ResultSet results = selectStatment.executeQuery(selectSql);

        String name="";
        String password="";
        while (results.next()) {
            name = results.getString("Users");
            password = results.getString("Password");
        }
        selectStatment.close();

        return new Login(name,password);
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
