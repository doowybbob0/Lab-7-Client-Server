package commands_server;

import java.util.Collections;

import server.CCollection;

public class C_filter_greater_than_health {
	public Object filter_greater_than_health(String value) {
        if (CCollection.collection.size() != 0) {        
        	StringBuilder healthString = new StringBuilder();
            long key = 1;  
            long a = 1;
            int actualValue=Integer.parseInt(value);
            while (key <=10000) {	
            	if (CCollection.collection.get(key) != null) {
		            if (CCollection.collection.get(key).getHealth()>actualValue) {
		            	healthString.append("Key value: " + key + " | Name: " + CCollection.collection.get(key).getName() + " | Coordinates: (" + CCollection.collection.get(key).getCordinatesX() + ";" + CCollection.collection.get(key).getCordinatesY() + ")" + " | Health Points: " + CCollection.collection.get(key).getHealth() + " | Weapon Type: " + CCollection.collection.get(key).getWeapon() + " | Melee Weapon Type: " + CCollection.collection.get(key).getMeleeWeapon() + " | Chapter Description (" + CCollection.collection.get(key).getChapter().getLegion() + ";" + CCollection.collection.get(key).getChapter().getSquad() + ")");
		            	healthString.append(System.lineSeparator());
		            }      
            	}
            key++;
            }                                   
            return healthString;
            
        } return"Cannot execute because collection is empty";
    }
}

