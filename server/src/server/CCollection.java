package server;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import utils.Keys;
import utils.SpaceMarine;
import tables.*;
import utils.*;

/**
 * This Class is designed to sort and store collections that are used in this program
 */
public class CCollection {
    	    
    public static HashMap<Long, SpaceMarine> collectionBuffer;;
    public static Map<Long, SpaceMarine> collection;
    
    private static ZonedDateTime time;
    
    
    public static Map<Long, SpaceMarine> enscribing() throws FileNotFoundException {
        collection = new HashMap<Long, SpaceMarine>();
        collectionBuffer =new HashMap<Long, SpaceMarine>();
        collection = Collections.synchronizedMap(collectionBuffer);
        long key=1;
        time=ZonedDateTime.now();
        Connection dbConnection2 = null;
        Statement statement2 = null;
        String selectTableSQL = "SELECT ID,NAME,X,Y,HEALTH,HEIGHT,WEAPON,MELEEWEAPON,LEGION,SQUAD,AUTHOR from data";
        try {
            dbConnection2 = Table_Data.getDBConnection();
            statement2 = dbConnection2.createStatement();
            // выбираем данные с БД
            ResultSet rs = statement2.executeQuery(selectTableSQL);
            // И если что то было получено то цикл while сработает
            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                int x = rs.getInt(3);
                int y = rs.getInt(4);
                int health = rs.getInt(5);
                int height = rs.getInt(6);
                
                String weapondb = rs.getString(7);
                Weapon weapon=null;
                if (weapondb.equals("FLAMER")) {  weapon = Weapon.FLAMER; }
                else if (weapondb.equals("GRAV_GUN")) { weapon = Weapon.GRAV_GUN; }
                else if (weapondb.equals("GRENADE_LAUNCHER")) { weapon = Weapon.GRENADE_LAUNCHER; }
                else if (weapondb.equals("MULTI_MELTA")) { weapon = Weapon.MULTI_MELTA; }
                else if (weapondb.equals("PLASMA_GUN")) { weapon = Weapon.PLASMA_GUN; }
                
                String meleeweapondb = rs.getString(8);
                MeleeWeapon meleeweapon=null;
                if (meleeweapondb.equals("CHAIN_SWORD")) {  meleeweapon = MeleeWeapon.CHAIN_SWORD; }
                else if (meleeweapondb.equals("MANREAPER")) { meleeweapon = MeleeWeapon.MANREAPER; }
                else if (meleeweapondb.equals("POWER_BLADE")) { meleeweapon = MeleeWeapon.POWER_BLADE; }
                
                String legion = rs.getString(9);
                String squad = rs.getString(10);
                long author = rs.getLong(11);
                collection.put(id,new SpaceMarine(name, new Coordinates(x, y), health, height, weapon, meleeweapon, new Chapter(legion, squad),id,author));
                key++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
        
            /**long i = 1;
            int n = collection.size();
            if (collection.size() != 0){
            
            	while (i <= n) {
            		System.out.print("Element ID " + "["+collection.get(i) + "]:" + collection.get(i).getId()+" | ");
            		i++;
            	}
            	System.out.println();
            }
         time = ZonedDateTime.now();
         return collection;**/

    }
    
    
    
    
    
    public static Map<Long, SpaceMarine> getCollection() {
        return collection;
    }
    public static ZonedDateTime getTime() {
        return time;
    }

    public static void setCollection(HashMap<Long, SpaceMarine> collection) {
        CCollection.collection = collection;
    }
}
