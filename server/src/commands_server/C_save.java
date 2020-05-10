package commands_server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import server.CCollection;
import server.SpaceMarineLists;

public class C_save {
	public static void save(String path) {    		
        long k = 1;
        SpaceMarineLists lists = new SpaceMarineLists();
        List lst = new ArrayList();
        while (k <= CCollection.collection.size()) {
            lst.add(CCollection.collection.get(k));
            k++;
        }
        lists.setSpaceMarineList(lst);
        String filepath = path+".json";
        PrintWriter pwDefault;
        try {       //JSON Conversion
        	if (path!=null) { pwDefault = new PrintWriter(new File(filepath));}
        	else { pwDefault = new PrintWriter(new File("Collection.json"));}
        	
        	String json = new Gson().toJson(lists);
        	pwDefault.print(json);
        	pwDefault.close();
        	System.out.println("Collection has been saved");
        } 
        catch(FileNotFoundException e1) {
        	System.out.println("Error has occured while saving the program");
        	e1.printStackTrace();
        }
        
    }
	
	public static void save() {    
		String path="Collection";
        long k = 1;
        SpaceMarineLists lists = new SpaceMarineLists();
        List lst = new ArrayList();
        while (k <= CCollection.collection.size()) {
            lst.add(CCollection.collection.get(k));
            k++;
        }
        lists.setSpaceMarineList(lst);
        String filepath = path+".json";
        PrintWriter pwDefault;
        try {       //JSON Conversion
        	if (path!=null) { pwDefault = new PrintWriter(new File(filepath));}
        	else { pwDefault = new PrintWriter(new File("Collection.json"));}
        	
        	String json = new Gson().toJson(lists);
        	pwDefault.print(json);
        	pwDefault.close();
        	System.out.println("Collection has been saved");
        } 
        catch(FileNotFoundException e1) {
        	System.out.println("Error has occured while saving the program");
        	e1.printStackTrace();
        }
        
    }
}
