package commands_client;
import java.util.Scanner;

import client.Adding;
import utils.SpaceMarine;

import java.io.*;

public class C_insert_key {
	public static  SpaceMarine insert_key(Scanner scanner)  {

        try {       
	        SpaceMarine space = Adding.Scan2(scanner);
	        if (space != null) {
	            //CCollection.collection.put(keys, space);
	        	return space;
	        }
        }
        catch (Exception ex) {
            System.out.println("Input data is incorrect"); }
        return null;
    }
}
