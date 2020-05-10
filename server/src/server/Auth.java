package server;
import tables.*;
import utils.User;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Данный класс используется для общения клиент-пользователь при авторизации
 */

public class Auth {	
	static String UserLogin;
    public static boolean login(Socket client) throws SQLException, IOException {
    	String user_login=null;
        try {
            DataInputStream userInput = new DataInputStream(client.getInputStream());
            DataOutputStream serverOutput = new DataOutputStream(client.getOutputStream());  
            
                        
            String user_password;
            boolean isCorrectLogin = false;
            int f=1;
            int n = 0;
            long id;
            long user_id;
            
            while (true) {         
            	CurrentUsers.collection();
            	user_login = userInput.readUTF();
            	System.out.println("[AUTH] An attempt to log in with ["+user_login+"] username");
            	id=1; 
            	user_id=1;
                while (id <= CurrentUsers.mapped_users.size()) {
                	if (CurrentUsers.mapped_users.get(user_id) != null) {
	                    if (user_login.equals(CurrentUsers.mapped_users.get(user_id).getLogin())) {
	                        serverOutput.write(1);
	                        
	                        while (true) {	                        	
	                        	user_password = userInput.readUTF();
	                        	if (user_password.equals(CurrentUsers.mapped_users.get(user_id).getParol())) {
	                                serverOutput.write(1); 
	                                Long current_user_id = Table_Users.getIDByName(user_login);
	                                serverOutput.writeLong(current_user_id); 
	                                UserLogin=user_login;
	                                System.out.println("[AUTH] User ["+user_login+"] was authorized");
	                                return true;
	                            } else {
	                                serverOutput.write(0);                               
	                            }
	                        }
	
	                    } else {                    	
	                        id++;
	                        user_id++;
	                    }
	                 
                	}
                	
                }
                serverOutput.write(0);               
            }

        }catch(Exception e) {System.out.println("[AUTH]  Failed to authorize user ["+user_login+"]");}
        return false;
    }
}
