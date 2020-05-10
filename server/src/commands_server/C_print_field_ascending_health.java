package commands_server;

import server.CCollection;

public class C_print_field_ascending_health {
	public Object print_field_ascending_health() {
		StringBuilder healthString = new StringBuilder();
    	int a = 0;
        long key = 0;
        int primeHealth=1000000000;
        long phID;
        if (CCollection.collection.size() != 0) {
        	while(a!=1000) {
        		key=0;        		
	        	while(key!=10000) {
	            key++;            
	            	if (CCollection.collection.get(key) != null) {                        		
	            
	            		if (CCollection.collection.get(key).getHealth() == a) {
	            			healthString.append("Key value: " + key + " | Name: " + CCollection.collection.get(key).getName() + " | Coordinates: (" + CCollection.collection.get(key).getCordinatesX() + ";" + CCollection.collection.get(key).getCordinatesY() + ")" + " | Health Points: " + CCollection.collection.get(key).getHealth() + " | Weapon Type: " + CCollection.collection.get(key).getWeapon() + " | Melee Weapon Type: " + CCollection.collection.get(key).getMeleeWeapon() + " | Chapter Description (" + CCollection.collection.get(key).getChapter().getLegion() + ";" + CCollection.collection.get(key).getChapter().getSquad() + ")");
	            			healthString.append(System.lineSeparator());
	            		}
	            	
	            	}
	            }
	        a++;
        	}        
        }else return "Collection is empty";
        return healthString;
    }
}
