package com.sorinbratosin.BestGPUPrice.Database;

import java.sql.*;

public class ConnectMySQL {

    private boolean connected;

    private Connection connection;
    private Statement statement;

    public ConnectMySQL() {
        connectToDB();
        connected = true;
    }

    public void connectToDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gpu_price", "root" , "Topkek93"); //"jdbc:mysql://127.0.0.1:3306/gpu_price\",\"root\",\"Topkek93\""
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            connected = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Statement getStatement() {
        return statement;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeStatement() throws SQLException {
        statement.close();
    }

    public void closeConnection() throws SQLException {
        connection.close();
        connected = false;
    }

    public boolean isConnected() {
        if(connection == null) {
            connected = false;
        }
        return connected;
    }

}
