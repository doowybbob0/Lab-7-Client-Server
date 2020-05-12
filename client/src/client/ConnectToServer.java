package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayDeque;
import java.util.Scanner;

import commands_client.C_help;
import commands_client.C_insert_key;
import utils.Comand;

public class ConnectToServer {
	
	
    private static ObjectOutputStream shipToServer;
    private static ObjectInputStream receiveFromServer;
    private static ArrayDeque<String> historyQueue = new ArrayDeque<String>();
    private static Boolean exitValue;   
    
	public void server_tunnel() {
		Scanner scanner = new Scanner(System.in);
        try {
        	exitValue=false;
            
            while (true && exitValue!=true) {
                try (Socket outcoming = new Socket(InetAddress.getLocalHost(), 28553)) {
                	outcoming.setSoTimeout(5000);
                    try {                        	
                        boolean isAuthorized = Auth.serverAuth(outcoming);                                            
                        if(isAuthorized) {                        	
                        	Thread.sleep(1000);
                        	ObjectOutputStream outputStream = new ObjectOutputStream(outcoming.getOutputStream());
                            ObjectInputStream inputStream = new ObjectInputStream(outcoming.getInputStream());
                            shipToServer = outputStream;
                            receiveFromServer = inputStream;
                        	interactiveMode();
                        }
                        
                        exitValue=true;
                        System.out.println("[MANAGER] Execution finished");                        
                    }catch (Exception e) {e.printStackTrace();}
                } catch (IOException e) {
                    System.err.println("[!]An error occured during connection to the server. Try again? [yes/no]");
                    //e.printStackTrace();
                    String answer;
                    while (!(answer = scanner.nextLine()).equals("yes") && exitValue!=true) {
                        switch (answer) {
                            case "":
                                break;
                            case "no":
                                exitValue=true;
                                break;
                            default: System.out.println("[!]Incorrect input");
                        }
                    }
                    if (exitValue!=true)System.out.println("Connecting ...");
                    continue;
                }
            }
        }catch (Exception e) {e.printStackTrace();}
    }

	

	
	private Boolean interactiveMode() throws IOException {
		System.out.println("[MANAGER] Launching remote collection manager");
        try (Scanner userCommandReader = new Scanner(System.in)){  //Bad tone. Causes hella troubles with streams      	                    	                   
            System.out.println("[MANAGER] Collection is ready for formatting");
            System.out.println("Waiting for input. Type help for list of commands");
            Thread.sleep(1000);                                                       
            
            String userCommand="";
            String[] finalUserCommand;	
            
            while (!userCommand.equals("exit")) {   
            	//System.out.println(userCommandReader.hasNextLine());	
            	if (userCommandReader.hasNextLine()){
	            	//System.out.println(userCommandReader.hasNextLine());	            
	                userCommand = userCommandReader.nextLine();
	                userCommand = userCommand.trim();
	                finalUserCommand = userCommand.trim().split(" ", 2);
	                
	                historyQueue.add(finalUserCommand[0]);
	                int historyCounter=0;
	            	for(String i: historyQueue) {historyCounter++;}
	            	if (historyCounter>14) {historyQueue.poll();}
	            	
	                Comand bufferedCommand = null;
	                switch (finalUserCommand[0]) {	                	                	
	                
	                    case ("info"):
	                        bufferedCommand = Comand.info;
	                        shipToServer.writeObject(bufferedCommand);
	                        shipToServer.flush();
	                        Object inf = receiveFromServer.readObject();
	                        System.out.println(inf);
	                        break;    
	                        
	                    case ("remove_key"):
	                        bufferedCommand = Comand.remove_key;	                                
	                        Scanner com = new Scanner(System.in);
	                        try {
	                        	long k= Long.parseLong(finalUserCommand[1]);
	                            shipToServer.writeObject(new CommandsManager(bufferedCommand, k).getComand());
	                            shipToServer.flush();
	                            shipToServer.writeObject(new CommandsManager(bufferedCommand, k).getL());
	                            shipToServer.flush();
	                            Object rem = receiveFromServer.readObject();
	                            System.out.println(rem);
	                        } catch (RuntimeException e) {
	                            System.out.println("Incorrect input data. Expected number ");
	                            System.out.println("Command execution terminated");
	                        }
	                        break;
	                        
	                        
	                    case ("remove_greater_key"):	                    	
	                        bufferedCommand = Comand.remove_greater_key;
	                        Scanner com4 = new Scanner(System.in);
	                        try {
	                            long kk = Long.parseLong(finalUserCommand[1]);
	                            shipToServer.writeObject(new CommandsManager(bufferedCommand, kk).getComand());
	                            shipToServer.flush();
	                            shipToServer.writeObject(new CommandsManager(bufferedCommand, kk).getL());
	                            shipToServer.flush();
	                            Object reml = receiveFromServer.readObject();
	                            System.out.println(reml);
	                        } catch (RuntimeException e) {
	                        	System.out.println("Invalid input");
	                            System.out.println("Command execution terminated");
	                        }
	                        break;
	                        
	                    case "replace_if_lower":
	                        bufferedCommand = Comand.replace_if_lower;
	                        Scanner comp = new Scanner(System.in);
	                        try {
	                            shipToServer.writeObject(new CommandsManager(bufferedCommand).getComand());
	                            long ke = Long.parseLong(finalUserCommand[1]);
	                            shipToServer.writeObject(new CommandsManager(bufferedCommand, ke).getL());
	                            shipToServer.flush();
	                            System.out.println("What would you like to change?:1-Name;2-Coords;3-HP");
	                            int anInt = comp.nextInt();
	                            while (anInt != 1 && anInt != 2 && anInt != 3) {
	                                System.out.println("Invalid input");
	                                System.out.println("What would you like to change?:1-Name;2-Coords;3-HP");
	                                anInt = comp.nextInt();
	                            }
	                            shipToServer.writeObject(new CommandsManager(bufferedCommand, anInt).getI());
	                            if (anInt == 1) {
	                                System.out.println("Enter new name");
	                                String name2 = comp.next();
	                                shipToServer.writeObject(new CommandsManager(bufferedCommand, name2).getString());
	                            }
	                            if (anInt == 2) {
	                                System.out.println("Enter new coords:");
	                                System.out.print("x:");
	                                int x2 = comp.nextInt();
	                                shipToServer.writeObject(new CommandsManager(bufferedCommand, x2).getI());
	                                System.out.print("y:");
	                                int y2 = comp.nextInt();
	                                shipToServer.writeObject(new CommandsManager(bufferedCommand, y2).getI());
	                            }
	                            if ((anInt == 3)) {
	                                System.out.print("Enter new HP value:");
	                                Integer hel = comp.nextInt();
	                                while (hel<0) {
	                                	System.out.println("Health Points can only be 0 or more than 0");
	                                	System.out.println("Enter Health Points:");
	                                	hel = comp.nextInt();
	                                }
	                                shipToServer.writeObject(new CommandsManager(bufferedCommand, hel).getI());
	                            }
	                            shipToServer.writeObject(new CommandsManager(bufferedCommand, anInt).getI());
	                            shipToServer.flush();
	                            Object repl = receiveFromServer.readObject();	                                    
	                            System.out.println(repl);
	                        } catch (RuntimeException e) {
	                            System.out.println("Invalid input");
	                            System.out.println("Command execution terminated");
	                        }
	                        break;
	                        
	                    case "filter_greater_than_health":
	                        bufferedCommand = Comand.filter_greater_than_health;
	                        Scanner com3 = new Scanner(System.in);
	                        try {
	                            int l = Integer.parseInt(finalUserCommand[1]);
	                            while (l<0) {
	                                System.out.println("Invalid HP value. Can only be greater than 0. Try again");
	                                l = com3.nextInt();
	                            }
	                            shipToServer.writeObject(new CommandsManager(bufferedCommand).getComand());
	                            shipToServer.flush();
	                            shipToServer.writeObject(new CommandsManager(bufferedCommand, l).getI());
	                            shipToServer.flush();
	                            Object remg = receiveFromServer.readObject();
	                            System.out.println(remg);
	                        } catch (RuntimeException e) {
	                        	System.out.println("Invalid input");
	                            System.out.println("Command execution terminated");
	                        }
	                        break;
	                        
	                    case ("help"):
	                        System.out.println(C_help.help());
	                        break;
	                        
	                    case ("show"):
	                        bufferedCommand = Comand.show;
	                        shipToServer.writeObject(bufferedCommand);
	                        shipToServer.flush();
	                        Thread.sleep(500);
	                        Object sh = receiveFromServer.readObject();
	                        System.out.println(sh);
	                        break;
	                        
	                    case ("print_field_ascending_health"):
	                        bufferedCommand = Comand.print_field_ascending_health;
	                        shipToServer.writeObject(bufferedCommand);
	                        shipToServer.flush();
	                        Thread.sleep(500);
	                        Object pfah = receiveFromServer.readObject();
	                        System.out.println(pfah);
	                        break;
	                        
	                    case ("insert_key"):
	                        bufferedCommand = Comand.insert_key;
	                        shipToServer.writeObject(bufferedCommand);
	                        shipToServer.flush();
	                        Thread.sleep(500);
	                        shipToServer.writeObject(C_insert_key.insert_key(userCommandReader));
	                        System.out.println("Element insert finished");
	                        break;
	                        
	                    case "update_id":
	                        bufferedCommand = Comand.update_id;
	                        Thread.sleep(500);	                                
	                        Scanner com2 = new Scanner(System.in);
	                        try {
	                            long l1 =Long.parseLong(finalUserCommand[1]);
	                            
	                            shipToServer.writeObject(bufferedCommand);
	                            shipToServer.flush();
	                            shipToServer.writeObject(new CommandsManager(bufferedCommand, l1).getL());	                                    
	                            shipToServer.flush();
	                            shipToServer.writeObject(C_insert_key.insert_key(userCommandReader));
	                            shipToServer.flush();
	                            Object up = receiveFromServer.readObject();
	                            System.out.println(up);
	                        } catch (RuntimeException e) {	                                	
	                            System.out.println("Incorrect Input Data");
	                            System.out.println("Update execution terminated");
	                        }
	                        break;
	                        
	                    case ("print_unique_chapter"):
	                        bufferedCommand = Comand.print;
	                        shipToServer.writeObject(bufferedCommand);
	                        shipToServer.flush();
	                        Object pr = receiveFromServer.readObject();
	                        System.out.println(pr);
	                        break;
	                        	                    	                       	                   
	                        
	                    case ("clear"):
	                        bufferedCommand = Comand.clear;
	                        shipToServer.writeObject(bufferedCommand);
	                        shipToServer.flush();
	                        Object cl = receiveFromServer.readObject();
	                        System.out.println(cl);	                                
	                        System.out.println("Done");
	                        break;
	                        	                    
	                        
	                    /**case "execute_script":
	                        bufferedCommand = Comand.execute_script;
	                        shipToServer.writeObject(bufferedCommand);
	                        shipToServer.flush();
	                        System.out.println("Enter script-file name: ");
	                        String file = userCommandReader.nextLine();
	                        CommandsManager sc = new CommandsManager(bufferedCommand, file);
	                        shipToServer.writeObject(sc.getString());
	                        Object sc2 = receiveFromServer.readObject();
	                        System.out.println(sc2);
	                        break;**/
	                        
	                    case "history":
	                    	System.out.println("Here's your input history:");
	                    	for(String i: historyQueue)
	                            System.out.println(i);
	                    	break; 
	                    	
	                    case ("change_password"):
	                        bufferedCommand = Comand.change_password;
	                        shipToServer.writeObject(bufferedCommand);
	                        shipToServer.flush();	    
	                        System.out.println("[AUTH] Enter new password");
	                        shipToServer.writeObject(userCommandReader.nextLine());
	                        shipToServer.flush();
	                        System.out.println("[AUTH] Your password was updated");
	                        break;
	                        
	                    case ("exit"):
	                        bufferedCommand = Comand.exit;
	                        shipToServer.writeObject(bufferedCommand);
	                        shipToServer.flush();                       
	                        receiveFromServer.close();
	                        shipToServer.close();
	                        return true;
	                        
	                    default:
	                        System.out.println("Unknown command. Type help for list of commands ");
	                }
            	}
	                
            }
            
    }catch (Exception e ) {	        	
        System.out.println("Failed to establish connection with server");
        System.out.println("Please, try again later");
        e.printStackTrace();}
        System.out.println("Connection terminated");
        return false;
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
