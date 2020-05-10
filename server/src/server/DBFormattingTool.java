package server;

import java.util.Scanner;

import tables.Table_Data;
import tables.Table_Users;


public class DBFormattingTool {		
	
	private static Scanner sInput;

	/**
	 * This method is made for database formatting
	 */
	public static void start() {
		sInput = new Scanner(System.in);
		Boolean exitValue=false;
		Boolean exitValueExtra=false;
		System.out.println("[WARNING] Would you like to start Database Formatting tool? [yes/no]");
		String doFTool = sInput.nextLine();

		while (!(doFTool.equals("yes")) && exitValue==false) {
            switch (doFTool) {                
                case "no":
                    exitValue=true;
                    break;
                default: System.out.println("Incorrect input"); doFTool = sInput.nextLine();
            }
		}
		
		if (doFTool.contentEquals("yes")) {			
			String userCommand = "";
	        String[] finalUserCommand;
	        while (exitValueExtra==false) {
	    		System.out.println("Waiting for input");
	            userCommand = sInput.nextLine();
	            userCommand = userCommand.trim();
	            finalUserCommand = userCommand.trim().split(" ", 2);	            
	            try {                	
	                switch (finalUserCommand[0]) {
	                
	                	case "new_users_table":	                		
	                		System.out.println("Creating new users table in database");
	                		Table_Users.new_table();
	                		System.out.println("Done");	                		
	                		break;
	                		
	                	case "delete_users_table":
	                		System.out.println("Deleting users table from database...");
	                		Table_Users.delete_table();
	                		System.out.println("Done");
	                		break;
	                		
	                	case "new_data_table":	                		
	                		System.out.println("Creating new data table in database");
	                		Table_Data.new_table();
	                		System.out.println("Done");	                		
	                		break;
	                	
	                	case "delete_data_table":
	                		System.out.println("Deleting users table from database...");
	                		Table_Data.delete_table();
	                		System.out.println("Done");
	                		break;	
	                		
	                	case "new_user":
	                		System.out.print("Enter new user's login:");
	                		String userName = sInput.nextLine();
	                		System.out.print("Enter new user's password:");
	                		String userPassword = sInput.nextLine();
	                		Table_Users.add_user(userName, userPassword);	                		
	                		break;
	                			                	
	                	case "exit":
	                		exitValueExtra=true;
	                		break;
	                	
	                	case "help":
	                		System.out.println("[new_user] Registers new user");
	                		System.out.println("[new_users_table] Creates new users table");
	                		System.out.println("[delete_users_table] Deletes users table");
	                		System.out.println("[new_data_table] Creates new data table");
	                		System.out.println("[delete_data_table] Deletes data table");
	                		System.out.println("[exit] Exits DBF tool");
	                		break;
	                		
	                	default: System.out.println("No such command. Type [help] for list of commands");
	                }                
	            }catch (Exception  ex) {
	            	ex.printStackTrace();
	            	System.out.println("Wrong arguements!");
	                System.out.println("Waiting for input");}
	        }
		}
		else System.out.println("Skipping DBF tool");
		
		System.out.println("Exiting DBF tool");
		
		
		
	}
}
