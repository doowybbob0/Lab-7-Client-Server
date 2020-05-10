package server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Scanner;

import commands_server.C_save;
import commands_server.C_show;
import tables.Table_Users;

public class ServerPrompt implements Runnable {		   
	    
		Scanner commandReader = new Scanner(System.in);
	    
	    @Override
	    public void run() {
	    	
	    	String userCommand = "";
            String[] finalUserCommand;
            while (true) {
                userCommand = commandReader.nextLine();
                userCommand = userCommand.trim();
                finalUserCommand = userCommand.trim().split(" ", 2);// trim().split-находит слова убирая пробел

                try {                	
                    switch (finalUserCommand[0]) {
                    	case "save":
                    		try{C_save.save(finalUserCommand[1]);}catch(ArrayIndexOutOfBoundsException arex) {C_save.save();}                    		
                    		break;
                    	case "show":
                    		System.out.println("Receiving data...");
                    		System.out.println(new C_show().show());
                    		break;
                    	case "new_user":
	                		System.out.print("Enter new user's login:");
	                		String userName = commandReader.nextLine();
	                		System.out.print("Enter new user's password:");
	                		String userPassword = commandReader.nextLine();
	                		Table_Users.add_user(userName, userPassword);	                		
	                		break;
                    	default: System.out.println("Only [save <path>],[new_user] and [show] are available");
                    }                
                }catch (Exception  ex) {
                	ex.printStackTrace();
                	System.out.println("Wrong arguements!");
                    System.out.println("Waiting for input");}
            }
            
	    }
}	   
