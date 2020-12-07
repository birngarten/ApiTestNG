package utilities;

import java.sql.*;

public class DatabaseConnector {

    private static final String dbusername = ConfigurationReader.getProperty("username"); // baglanti kurar
    private static final String dbpassword = ConfigurationReader.getProperty("password"); // sorgu yapar
    private static final String connectionUrl = ConfigurationReader.getProperty("db_url"); // sonuclari topluyor

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static ResultSet getResultSet(String query) {

        try {
            connection = DriverManager.getConnection(connectionUrl, dbusername, dbpassword);
            if (connection != null) {
                System.out.println("EN: Connected to the database...");
                System.out.println("TR: Database e baglanildi...");
            } else {
                System.out.println("EN: Database connection failed");
                System.out.println("TR: Database baglantisi kurulamadi.");
            }

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(query);

        } catch (SQLException sqlEx) {
            System.out.println("SQL Exception:" + sqlEx.getStackTrace());
        }
        return resultSet;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                System.out.println("Database Connection kapatiliyor...");
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
