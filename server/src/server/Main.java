package server;
//Importing stuff for JSON parsing
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.*;

//import server.ClientManager.ForkTaskPool;

import java.net.SocketException;
import java.io.*;
import java.util.*;



/**
 * Executive Main Class. Serves for Saving/Reading and Script Running purposes
 */
public class Main {
	
	static ExecutorService executeIt = Executors.newFixedThreadPool(2);
	ForkJoinPool clientPool=ForkJoinPool.commonPool();
	static  int available_resources_pool = Runtime.getRuntime().availableProcessors();
	
	
    /**
     * Asks user to fill in desired file path via environment variable
     */			

	public static String inputFilePath() throws FileNotFoundException {
		String filePath = System.getenv("filePath");
        if (filePath == null){
            return null;
        } else {
                return filePath;
            }
    }


    
    /**
     * Loads data from defined file location. Reads json file and then forms it in the collection.
     */	
	/**
    public static void loadJson(String localPath) throws FileNotFoundException, com.google.gson.JsonSyntaxException {    	
    	try {
	    	if (localPath==""){localPath=inputFilePath();}  //Asks user for path input via inputFilePath()
	    	if (inputFilePath()==null){System.out.println("Collection.json loaded due to lack of filePath system variable");localPath="Collection.json";}
	    	
	    	Scanner scDefault = new Scanner(new File(localPath));
	    	String preloadedData = scDefault.nextLine();
	    	//System.out.println(preloadedData);
	    	Gson gsonHandler = new Gson();
	        SpaceMarineLists handledPreloadedList = gsonHandler.fromJson(preloadedData, SpaceMarineLists.class);
	        if (handledPreloadedList.SpaceMarineList !=null) {
	            CCollection.collectionBuffer.addAll(handledPreloadedList.SpaceMarineList);
	            //System.out.println(CCollection.collectionBuffer);
	            if (CCollection.collectionBuffer.size() <= 0) {System.out.println("=====This file's collection is empty=====");
	            System.out.println("Type raw_start to load prerecorded collection");}
	        }else {System.out.println("=====This file's collection is empty=====");
	        System.out.println("Type raw_start to load prerecorded collection");}
    	}catch (JsonSyntaxException eC){
    		System.out.println("[ERROR]: Your json file is corrupted (╯°□°）╯︵ ┻━┻");
    		System.out.println("...but you still can load default collection by typing in raw_start...");
    	}catch (FileNotFoundException eFNF){System.out.println("Unable to reach your file. Check your path and try again ");}         
    }      **/
    
    
    /**
     * This method runs the whole program
     */
    public static void main(String[] args) throws IOException,SocketException {
    	System.out.println("Starting server");
    	ForkJoinPool fjPool = new ForkJoinPool(available_resources_pool);
    	DBFormattingTool.start();
    	    try {
    		ServerSocket server = new ServerSocket(28553);
            System.out.println("Server started"); 
            
            executeIt.execute(new ServerPrompt()); //Runs server-side prompt
            while (true) {             
            	//Main.loadJson("");	 //loading collection. If empty, you should use environment variable filePath
                CCollection.enscribing();
                
              
                while (!server.isClosed()) {
            	  
                	Thread.sleep(500);
                	Socket client = server.accept();// Client's waiting for connection                  
                	System.out.println("New connection established");  
                	fjPool.execute(new ClientManager(client));
                	//executeIt.execute(new ClientManager(client));
                	System.out.println("New Client profile created");
                  
                	Thread.sleep(300);
              }
              //executeIt.shutdown();
            }
            
          // Closing pool
      } catch (IOException | InterruptedException  e) {System.out.println("Failed to start server.");}    	    
    }      
}