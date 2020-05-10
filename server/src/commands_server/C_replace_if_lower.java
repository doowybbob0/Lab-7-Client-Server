package commands_server;

import java.sql.SQLException;
import java.util.Scanner;

import server.CCollection;
import server.TableDataFormatting;
import utils.Coordinates;
import utils.SpaceMarine;

public class C_replace_if_lower {
	

	public Object replace(Scanner Replace, long key, String name, Long author) throws SQLException {
		//Name override
		//System.out.println("Enter new name");
        String name2 = name;
        if (name2.length() < CCollection.collection.get(key).getName().length()){
            SpaceMarine rep = new SpaceMarine(name2, new Coordinates(CCollection.collection.get(key).getCordinatesX(), CCollection.collection.get(key).getCordinatesY()), CCollection.collection.get(key).getHealth(), CCollection.collection.get(key).getHeight(), CCollection.collection.get(key).getWeapon(),CCollection.collection.get(key).getMeleeWeapon(), CCollection.collection.get(key).getChapter(), CCollection.collection.get(key).getId(),author);
            try{TableDataFormatting.update_element_name(CCollection.collection.get(key), name, key);}catch(SQLException e) {}
            CCollection.collection.put(key, rep);
            
        }
        return "Element updated";
	}

	public Object replace(Scanner Replace, long key, int x, int y, Long author) {
		//System.out.println("Enter new coordinates:");
        int x2 = x;
        int y2 = y;
        if (x2 + y2 < CCollection.collection.get(key).getCoordinates()){
            SpaceMarine rep = new SpaceMarine(CCollection.collection.get(key).getName(), new Coordinates(x2, y2), CCollection.collection.get(key).getHealth(), CCollection.collection.get(key).getHeight(), CCollection.collection.get(key).getWeapon(),CCollection.collection.get(key).getMeleeWeapon(), CCollection.collection.get(key).getChapter(), CCollection.collection.get(key).getId(),author);
            try{TableDataFormatting.update_element_coords(CCollection.collection.get(key), x,y, key);}catch(SQLException e) {}
            CCollection.collection.put(key, rep);
        }
        return "Element updated";
	}

	public Object replace(Scanner Replace, long key, int health, Long author) {
		//System.out.println("Enter new health points value:");
        Integer hel = health;
        if (hel < CCollection.collection.get(key).getHealth()){
            SpaceMarine rep = new SpaceMarine(CCollection.collection.get(key).getName(), new Coordinates(CCollection.collection.get(key).getCordinatesX(), CCollection.collection.get(key).getCordinatesY()), hel, CCollection.collection.get(key).getHeight(), CCollection.collection.get(key).getWeapon(),CCollection.collection.get(key).getMeleeWeapon(), CCollection.collection.get(key).getChapter(), CCollection.collection.get(key).getId(),author);
            try{TableDataFormatting.update_element_health(CCollection.collection.get(key), health, key);}catch(SQLException e) {}
            CCollection.collection.put(key, rep);
        }
		return "Element updated";
	}
	
    
    
    
    
}
