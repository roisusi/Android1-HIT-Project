package ZooServer;

import java.sql.*;

public class DataBase {

    private Connection con;

    public String getUserName(int idu) throws SQLException {
        //sipActivation.clear();
        String selectSql = "select User from Users where idUsers="+idu;
        Statement selectStatment = con.createStatement();

        ResultSet results = selectStatment.executeQuery(selectSql);
        String name="";
        while (results.next()) {
            name = results.getString("User");
        }
        selectStatment.close();

        return name;
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
