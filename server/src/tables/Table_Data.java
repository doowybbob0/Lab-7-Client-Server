package tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Table_Data {
	private static String URL = "jdbc:postgresql://localhost:5432/lab7"; //jdbc:postgresql://pg:5432/studs
    private static String Username = "postgres";
    private static String Password = "root";
    
    
    public static Connection getDBConnection() {
    	Connection dbConnection = null;
    	try {Class.forName("org.postgresql.Driver");} 
    	catch (ClassNotFoundException e) {System.out.println("[!]Failed to load postgresql driver");}
        try {
        	dbConnection = DriverManager.getConnection(URL, Username,Password);
            return dbConnection;
        }catch (SQLException e) {System.out.println("[!]Failed to establish connection with database");}
        return dbConnection;
     }
    
    
     public static void new_table() throws SQLException {
            System.out.println("Creating data table");
            Connection dbConnection = null;
            Statement statement = null;
            dbConnection = DriverManager.getConnection(URL, Username, Password);
            statement = dbConnection.createStatement();
            String sql = "CREATE TABLE data " +
                    "(ID SERIAL PRIMARY KEY NOT NULL," +
                    "NAME           TEXT   NOT NULL, " +
                    "X              INT    NOT NULL," +
                    "Y              INT    NOT NULL," +
                    "HEALTH         INT    NOT NULL, " +                    
                    "HEIGHT         INT    NOT NULL, " +
                    "WEAPON         TEXT   NOT NULL ," +
                    "MELEEWEAPON    TEXT   NOT NULL ," +                    
                    "LEGION         TEXT   NOT NULL," +
                    "SQUAD          TEXT   NOT NULL," +
                    "AUTHOR         INT    NOT NULL," +
                    "DATES          DATE )";                    
            try {
                // выполнить SQL запрос
                statement.execute(sql);
                System.out.println("Table <data> is created");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            }
        }
        public static void delete_table() throws SQLException {
            Connection dbConnection = null;
            Statement statement = null;
            dbConnection = DriverManager.getConnection(URL, Username,Password);
            statement = dbConnection.createStatement();
            String insertTableSQL = "drop table data;";
            statement.executeUpdate(insertTableSQL);
            System.out.println("Table <data> is deleted");
        }

        public static void delete_id(Long id) throws SQLException {
            Connection dbConnection = null;
            Statement statement = null;
            dbConnection = DriverManager.getConnection(URL, Username,Password);
            statement = dbConnection.createStatement();
            String sql = "DELETE from data where ID="+id+";";
            statement.executeUpdate(sql);
            dbConnection.close();
            statement.close();
        }
}
