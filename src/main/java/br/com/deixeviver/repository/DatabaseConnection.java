package br.com.deixeviver.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/DEIXE_VIVER";
    private static final String USER = "root";
    private static final String PASSWORD = "0000";

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static DatabaseConnection instance = null;

   public static synchronized DatabaseConnection getInstance() {
       if (instance == null) {
           instance = new DatabaseConnection();
       }
       return instance;

   }


   public Connection getConnection() throws SQLException {

       return DriverManager.getConnection(URL, USER, PASSWORD);
   }


   public static void closeConnection(Connection conn) {
       if(conn != null) {
           try{
               conn.close();
           } catch (SQLException e) {
               System.out.println("Erro ao fechar a conexão: " + e.getMessage());
           }
       }

   }




}