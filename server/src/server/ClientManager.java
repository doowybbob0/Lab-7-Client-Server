package server;
import com.google.gson.Gson;

import commands_server.*;
import tables.Table_Users;
import utils.Comand;
import utils.Keys;
import utils.SpaceMarine;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.text.SimpleDateFormat;
import java.util.Date; 

public class ClientManager  implements Runnable  {

    private static Socket clientDialog;
    static  int available_resources_pool = Runtime.getRuntime().availableProcessors();
    public static Scanner commandReader= new Scanner(System.in);
    String[] finalServerCommand;
    String currentUserName;
    
    public ClientManager(Socket client) { ClientManager.clientDialog = client; }
    
    
    
    
    @Override
    public void run() {
    	
	    try {  	    		    	
	    	boolean isAuthorized = false;
	    	try {isAuthorized = Auth.login(clientDialog);} 
	    	catch (SQLException | IOException e) {e.printStackTrace();}
	    	
	    	currentUserName=Auth.UserLogin;
	    	Long user_id=0L;
	    	     
	    	
	    	// Server socket initialization      
	    	ObjectInputStream inn = new ObjectInputStream(clientDialog.getInputStream());	        
	        // Reading from socket
	        ObjectOutputStream outt = new ObjectOutputStream(clientDialog.getOutputStream());
	    	clientDialog.isConnected();	    	
	    	
	    	
            Object com = null;                                                
            while (com != Comand.exit) {
            	
            	if (isAuthorized) {
            		user_id = Table_Users.getIDByName(currentUserName);            		
            		
            		ForkJoinPool fjPool = new ForkJoinPool(available_resources_pool);
            		
		            com = inn.readObject(); 		           	
		            
		            SimpleDateFormat cTimeFormatting = new SimpleDateFormat("HH:mm:ss"); //Future logs builder
		            Date cTime = new Date();                  
		            String k = String.valueOf(com);
		            System.out.println("["+cTimeFormatting.format(cTime)+"]"+"Executing command: "+k);
		                
	                switch (k) {
	                    
	                        case "show":
	                            String show = new C_show().show();
	                            //outt.writeObject(show);
	                            //String s1 = new Show().show();
	                            //Object show = new Show().show();
	                            outt.writeObject(fjPool.invoke(new ForkTaskPool(0,show)));
	                            break;	                            	                        
	                        
	                        case "print_field_ascending_health":
	                            Object pfah = new C_print_field_ascending_health().print_field_ascending_health();
	                            outt.writeObject(pfah);
	                            break;
	                            
	                        case "raw_start":
	                            Object rs= C_raw_start.raw_start();                            
	                            outt.writeObject(rs);
	                            break;
	                            
	                        case "insert_key":
	                            long keys;
	                            keys = Keys.keys();
	                            SpaceMarine sp = null;
	                            sp = (SpaceMarine) inn.readObject();
	                            TableDataFormatting.add_element(sp, user_id);
	                            CCollection.collection.put(keys, sp);
	                            break;
	                            
	                        case "update_id":
	                            long element_id;
	                            SpaceMarine spaceMarine = null;
	                            element_id = (long) inn.readObject();
	                            spaceMarine = (SpaceMarine) inn.readObject();
	                            if (CCollection.collection.get(element_id) != null) { 
		                            	if (user_id.equals(CCollection.collection.get(element_id).getAuthor())) {
		                            		TableDataFormatting.update_element(spaceMarine, CCollection.collection.get(element_id).getAuthor(),element_id);
			                                CCollection.collection.put(element_id, spaceMarine);	                                
			                                Object msg = String.valueOf("Element update sequence finished ");
			                                outt.writeObject(msg);
		                            	}else
		                            	{Object msg = String.valueOf("You are not authorized to edit this element");
		                                outt.writeObject(msg);}
	                            } else {
	                                Object msg = String.valueOf("Incorrect ID. Type show for list of IDs");
	                                outt.writeObject(msg);
	                            }
	                            break;
	                            
	                        case "remove_key":
	                            long kk = (long) inn.readObject();
	                            String kks = Long.toString(kk);
	                            if (CCollection.collection.get(kk) != null) {
	                            	if (user_id.equals(CCollection.collection.get(kk).getAuthor())) {
	                            		TableDataFormatting.delete_element(kk);
		                                Object remove = C_remove_key.remove1(kks);
		                                outt.writeObject(remove);
	                            	}else {
	                            		Object msg = String.valueOf("You are not authorized to edit this element");
		                                outt.writeObject(msg);
	                            	}
	                            } else {
	                                Object msg = String.valueOf("Вы ввели некоректное значение ключа. Для того чтобы узнать все значения у элементов коллекции - введите команду show");
	                                outt.writeObject(msg);
	                            }
	                            break;
	                            
	                            
	                        case "replace_if_lower":
	                            Scanner scanner = new Scanner(System.in);
	                            long rifID = (long) inn.readObject();
	                            int rifCategory = (int) inn.readObject();	                            
	                            if (CCollection.collection.get(rifID) != null) {
	                            	if (user_id.equals(CCollection.collection.get(rifID).getAuthor())) {
		                                if (rifCategory == 1) {
		                                    String name = (String) inn.readObject();		                                    
		                                    new C_replace_if_lower().replace(scanner, rifID, name,user_id);
		                                }
		                                if (rifCategory == 2) {
		                                    int x = (int) inn.readObject();
		                                    int y = (int) inn.readObject();                                    
		                                    new C_replace_if_lower().replace(scanner, rifID, x, y,user_id);
		                                }
		                                if ((rifCategory == 3)) {
		                                    int f = (int) inn.readObject();
		                                    new C_replace_if_lower().replace(scanner, rifID, f,user_id);
		                                }
		                                Object msg2 = String.valueOf("Element updated");
		                                outt.writeObject(msg2);
	                            	}else {
	                            		Object msg = String.valueOf("You are not authorized to edit this element");
		                                outt.writeObject(msg);
	                            	}
	                            } else {
	                                Object msg = String.valueOf("Invalid input data");
	                                outt.writeObject(msg);
	                            }
	                            break;
	                            
	                            
	                        case "remove_greater_key":
	                            long element_id_rgk = (long) inn.readObject();	                                                       
	                            if (CCollection.collection.get(element_id_rgk) != null) {
	                                Object remg = new C_remove_greater_key().remove_greater_key(element_id_rgk,user_id);
	                                outt.writeObject(remg);
	                            } else {
	                                Object msg = String.valueOf("Invalid key");
	                                outt.writeObject(msg);
	                            }
	                            break;
	                            
	                            
	                        case "filter_greater_than_health":                            
	                            int i = (int) inn.readObject();
	                            String hvalue= Integer.toString(i);
	                            Object fgth = new C_filter_greater_than_health().filter_greater_than_health(hvalue);
	                            outt.writeObject(fgth);
	                            break;                                                   
	                            
	                        case "info":
	                            Object inf = new C_info().info();
	                            outt.writeObject(inf);
	                            break;
	                            
	                        /**case "execute_script":
	                        	Scanner esscanner = new Scanner(System.in);
	                            String file = (String) inn.readObject();
	                            Object script = new C_execute_script().script(esscanner,file);
	                            outt.writeObject(script);
	                            break;**/
	                        case "clear":
	                            Object clear = new C_clear().clear();
	                            outt.writeObject(clear);
	                            break;
	                        case "exit":
	                            System.out.println("Client terminated connection");                            
	                            clientDialog.close();
	                            break;
	                        case "change_password":
	                            String new_password= (String) inn.readObject();
	                            Table_Users.change_password(user_id, new_password);	                            	                            
	                            break;
	                }            
                }else System.out.println("User is not authorized");
            }System.out.println("Waiting for new connection");
        } catch (IOException |SQLException |ClassNotFoundException e) {
        	//e.printStackTrace();
            System.out.println("An error occured during user ["+currentUserName+"] session. Connection terminated.");
            
        }
    }
    
    
    
    /*
     * Saves data to custom json file. Will be deleted in the future
     */
    public static void save(){
    	System.out.println("Trying to save your collection...");
    	String localPath;    	
    	
    	long k = 1;
        SpaceMarineLists lists = new SpaceMarineLists();
        List lst = new ArrayList();
        while (k <= CCollection.collection.size()) {
            lst.add(CCollection.collection.get(k));
            k++;
        }
        lists.setSpaceMarineList(lst);
        
        try {       //JSON Conversion
        	if (Main.inputFilePath()==null){localPath=Main.inputFilePath();}
        	else {localPath="Collection.json";}
        	PrintWriter pwDefault = new PrintWriter(new File(localPath));
        	String json = new Gson().toJson(lists);
        	pwDefault.print(json);
        	pwDefault.close();
        	System.out.println("Collection has been saved");
        } 
        catch(FileNotFoundException e1) {
        	
        	System.out.println("An error has occured while saving the program");
        	e1.printStackTrace();
        }
    }
    
    
    
    static class ForkTaskPool extends RecursiveTask<StringBuilder> {
        String ftpString;
        int counter;

        public ForkTaskPool(int i,String formattedBuilder){
            this.counter=i;
            this.ftpString=formattedBuilder;
        }

        @Override
        protected StringBuilder compute() {
            int i = -1;
            String spl = "!";
            String string = ftpString;
            StringBuilder compute_builder = new StringBuilder();
            String[] s = string.split(spl);            
            while (i!=s.length-1){
                i++;
                while (s[i]==null){i++;}
                String element = s[i];
                compute_builder.append(element+System.lineSeparator());
                ForkTaskPool f2 = new ForkTaskPool(i,ftpString); }            
            compute_builder.append(System.lineSeparator());
                return compute_builder;
        }
    }
}