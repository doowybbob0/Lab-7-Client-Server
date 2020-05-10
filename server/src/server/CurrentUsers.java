package server;

import utils.User; 
import tables.Table_Users;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CurrentUsers {
	public static HashMap<Long, User> collection_users;
    public static Map<Long, User> mapped_users;
    
    public static HashMap<String, String> collection() throws SQLException {
        collection_users=new HashMap<>();
        mapped_users = Collections.synchronizedMap(collection_users);
        long id = 1;
        Connection dbConnection2 = null;
        Statement statement2 = null;
        String selectTableSQL = "SELECT ID,NAME,PASSWORD from users";
        try {
            dbConnection2 = Table_Users.getDBConnection();
            statement2 = dbConnection2.createStatement();
            // выбираем данные с БД
            ResultSet rs = statement2.executeQuery(selectTableSQL);
            // И если что то было получено то цикл while сработает
            while (rs.next()) {                
                Long user_id  = rs.getLong("ID");
                String user_name = rs.getString("NAME");
                String user_password = rs.getString("PASSWORD");
                User user_summary = new User(user_name,user_password,user_id);
                mapped_users.putIfAbsent(id,user_summary);
                id++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    
    public static Long indkey(String log){
        long i =1;
        while (i< mapped_users.size()){
            if (mapped_users.get(i).getLogin().equals(log)){
                return i;
            }else {i++;}
        }
        return i;
    }
}
