package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Auth {
	
	public static Long user_id;
	
	public static Boolean serverAuth(Socket socket) {
		Scanner authScanner = new Scanner(System.in);
		
		System.out.println("[WARNING] Launching Auth tool");                
        try  {     
        		DataOutputStream authOut = new DataOutputStream(socket.getOutputStream());
        		DataInputStream authIn = new DataInputStream(socket.getInputStream());
                boolean loginStage=true;
                boolean passwordStage=true;
                while (loginStage) {
                	System.out.println("[AUTH] Enter your login:");
                    String user_login=authScanner.nextLine();
                    authOut.writeUTF(user_login);
                    int serverLoginAnswer = authIn.read();
                    if (serverLoginAnswer == 1) {
                    	loginStage=false;
                        while (passwordStage) {                        	
                            System.out.println("[AUTH] Enter your password:");
                            String user_password = authScanner.nextLine();                            
                            String encrypted_user_password = ConnectToServer.encrypt_data(user_password);
                            authOut.writeUTF(encrypted_user_password);                            
                            int serverAuthAnswer = authIn.read();
                            if (serverAuthAnswer == 1) {
                            	passwordStage=false;
                            	user_id=authIn.readLong();
                                System.out.println("[AUTH] You were authorized");
                                System.out.println("[AUTH] Your ID is ["+user_id+"]");
                                return true;                            
                                }
                            else if (serverAuthAnswer == 0) {
                                System.out.println("[AUTH] Wrong password");
                            }
                        }
                    }
                    else if (serverLoginAnswer==0) {
                            System.out.println("[AUTH] Incorrect login");                                                        
                    }                                        
                }
                                       
        }catch (Exception e){
            System.out.println("[AUTH][!] An error has occured");
            return false;
        }
		return false;
	}
	
	public static String new_password(String password) {
		String new_encrypted_password = ConnectToServer.encrypt_data(password);
		return new_encrypted_password;
	}
}
