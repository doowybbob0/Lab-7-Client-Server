package tables;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.SpaceMarine;

public class Table_Users {
	
	private static String URL = "jdbc:postgresql://localhost:5432/lab7";//"jdbc:postgresql://pg:5432/studs";
    private static String Username = "postgres";//postgres
    private static String Password = "root";//root
    
    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {Class.forName("org.postgresql.Driver");} 
        catch (ClassNotFoundException e) {System.out.println("[!]Failed to load postgresql driver");}
        try {
            dbConnection = DriverManager.getConnection(URL, Username,Password);
            return dbConnection;
        } catch (SQLException e) {System.out.println("[!]Failed to establish connection with database");}
        return dbConnection;
    }
    
    
    
    public static void new_table() throws SQLException {
        System.out.println("Creating users table");
        Connection dbConnection = null;
        Statement statement = null;
        dbConnection = DriverManager.getConnection(URL, Username,Password);
        statement = dbConnection.createStatement();
        String sql = "CREATE TABLE users " +
                "(ID SERIAL PRIMARY KEY NOT NULL," +
                "NAME           TEXT    NOT NULL, " +
                "PASSWORD        TEXT    NOT NULL)";
        try {

            statement.execute(sql);
            System.out.println("Table <users> is created");
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
        try {
	        dbConnection = DriverManager.getConnection(URL, Username,Password);
	        statement = dbConnection.createStatement();
	        String deleteTableSQL = "drop table users;";
	        statement.executeUpdate(deleteTableSQL);
	        System.out.println("Table <users> is deleted");
        } catch (SQLException e) {System.out.println("Failed to delete table");}
        
    } 
    
    public static void update_table() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        dbConnection = DriverManager.getConnection(URL, Username,Password);
        statement = dbConnection.createStatement();
        try {

            String insertTableSQL = "INSERT INTO users"
                    + "(NAME,PASSWORD) " + "VALUES"
                    + "('BBBB','22')";
            statement.executeUpdate(insertTableSQL);

            System.out.println("Элемент успешно добавлен");
        }catch (Exception e)
        {System.out.println("Элемент не может быть добавлен так как элемент с таким ID уже сущесвтует");}
    }
    
    public static void add_user(String username1,String password1) throws SQLException {
            PreparedStatement preparedStatement = null;
            Statement statement = null;
            Connection dbConnection2= null;             
            Long id=1L;
            String sqlStatement = "INSERT INTO users (NAME,PASSWORD) VALUES (?,?);";
            String encryptedPassword = encrypt_data(password1);            
            try {
                dbConnection2 = getDBConnection();;
                Boolean isOriginal = true;
                String selectTableSQL = "SELECT ID,NAME from users";
                statement = dbConnection2.createStatement();
                ResultSet rs = statement.executeQuery(selectTableSQL);                
                while (rs.next()) {
                    String username = rs.getString("NAME");
                    Long userid  = rs.getLong("ID");    
                    if (username.equals(username1)) {System.out.println("[!]This login is already registered");isOriginal=false;}
                    id++;
                }                
                if (isOriginal) {
                	preparedStatement = dbConnection2.prepareStatement(sqlStatement);
                	preparedStatement.setString(1,username1);
                	preparedStatement.setString(2,encryptedPassword);
                	preparedStatement.executeUpdate();
                	System.out.println("User registered successfully");
                }
                
            } catch (SQLException e) {
                System.out.println("[!]Failed to register new user");
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (dbConnection2!= null) {
                    dbConnection2.close();
                }
            }
        }
    
    
    
    
    public static Long getIDByName(String username_to_check) throws SQLException {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Connection dbConnection= null;                                      
        try {
            dbConnection = getDBConnection();            
            String selectTableSQL = "SELECT ID,NAME from users";
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);                
            while (rs.next()) {
                String username = rs.getString("NAME");
                Long userid  = rs.getLong("ID");    
                if (username.equals(username_to_check)) {return userid;}                
            }
            return null;
        }catch (SQLException e) {e.printStackTrace();return null;}
    }
    
    
    public static void change_password(Long user_id, String new_password) throws SQLException {
        Connection dbConnection2 = null;
        dbConnection2 = Table_Data.getDBConnection();
        String sql = "UPDATE users SET PASSWORD=? WHERE ID="+user_id+";";//
        PreparedStatement preparedStatement = dbConnection2.prepareStatement(sql);
        try {        
            preparedStatement.setString(1, new_password);             
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection2!= null) {
                dbConnection2.close();
            }
        }
    }
    
    
    public static String encrypt_data(String input) {
        try {
            // метод getInstance () вызывается с алгоритмом SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA1");
            // вызывается метод digest ()
            // вычислить дайджест сообщения из входной строки возвращается как массив байтов
            byte[] messageDigest = md.digest(input.getBytes());
            // Преобразование байтового массива в представление знака
            BigInteger no = new BigInteger(1, messageDigest);
            // Преобразуем дайджест сообщения в шестнадцатеричное значение
            String hashtext = no.toString(16);
            // Добавить предыдущие 0, чтобы сделать его 32-битным
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
}


