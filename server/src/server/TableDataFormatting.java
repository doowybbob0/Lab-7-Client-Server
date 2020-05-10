package server;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tables.Table_Data;
import utils.*;
/**
Used for SQL Data table formatting
 */
public class TableDataFormatting {
    public static void add_element(SpaceMarine element, Long user_id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection dbConnection2= null;        
        String sqlStatement = "INSERT INTO data"
                + "(NAME,X,Y,HEALTH,HEIGHT,WEAPON,MELEEWEAPON,LEGION,SQUAD,AUTHOR,DATES) " + "VALUES"
                + "(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            dbConnection2 = Table_Data.getDBConnection();;
            preparedStatement = dbConnection2.prepareStatement(sqlStatement);
            java.util.Date creationDate = element.getCreationDate();
            java.sql.Date sqlFormattedCreationDate = new java.sql.Date(creationDate.getTime());
            preparedStatement.setString(1, element.getName());
            preparedStatement.setInt(2, element.getCordinatesX());
            preparedStatement.setInt(3, element.getCordinatesY());
            preparedStatement.setInt(4, element.getHealth());
            preparedStatement.setInt(5, element.getHeight());
            preparedStatement.setString(6,String.valueOf(element.getWeapon()));
            preparedStatement.setString(7,String.valueOf(element.getMeleeWeapon()));
            preparedStatement.setString(8, element.getChapter().getLegion());
            preparedStatement.setString(9,element.getChapter().getSquad());            
            preparedStatement.setLong(10, user_id);
            preparedStatement.setDate(11, sqlFormattedCreationDate);            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("[TDF] An error has occured");
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection2!= null) {
                dbConnection2.close();
            }
        }
    }
    
    
    public static void delete_element(Long element_id) { try{Table_Data.delete_id(element_id);}catch(Exception e) {}}
    
    
    public static void update_element(SpaceMarine element ,Long user_id, Long element_id) throws SQLException {
        Connection dbConnection2 = null;
        dbConnection2 = Table_Data.getDBConnection();
        String sql = "UPDATE data SET NAME=?,X=?,Y=?,HEALTH=?,HEIGHT=?,WEAPON=?,MELEEWEAPON=?,LEGION=?,SQUAD=?,AUTHOR=?,DATES=? WHERE ID="+element_id+";";//
        PreparedStatement preparedStatement = dbConnection2.prepareStatement(sql);
        try {
        	java.util.Date creationDate = element.getCreationDate();
            java.sql.Date sqlFormattedCreationDate = new java.sql.Date(creationDate.getTime());
            preparedStatement.setString(1, element.getName());
            preparedStatement.setInt(2, element.getCordinatesX());
            preparedStatement.setInt(3, element.getCordinatesY());
            preparedStatement.setInt(4, element.getHealth());
            preparedStatement.setInt(5, element.getHeight());
            preparedStatement.setString(6,String.valueOf(element.getWeapon()));
            preparedStatement.setString(7,String.valueOf(element.getMeleeWeapon()));
            preparedStatement.setString(8, element.getChapter().getLegion());
            preparedStatement.setString(9,element.getChapter().getSquad());            
            preparedStatement.setLong(10, user_id);
            preparedStatement.setDate(11, sqlFormattedCreationDate);            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection2!= null) {
                dbConnection2.close();
            }
        }
    }
    
    
    public static void update_element_name(SpaceMarine element ,String name, Long element_id) throws SQLException {
        Connection dbConnection2 = null;
        dbConnection2 = Table_Data.getDBConnection();
        String sql = "UPDATE data SET NAME=? WHERE ID="+element_id+";";//
        PreparedStatement preparedStatement = dbConnection2.prepareStatement(sql);
        try {
            preparedStatement.setString(1, name);                    
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection2!= null) {
                dbConnection2.close();
            }
        }
    }
    
    public static void update_element_coords(SpaceMarine element ,Integer x, Integer y, Long element_id) throws SQLException {
        Connection dbConnection2 = null;
        dbConnection2 = Table_Data.getDBConnection();
        String sql = "UPDATE data SET X=?,Y=? WHERE ID="+element_id+";";//
        PreparedStatement preparedStatement = dbConnection2.prepareStatement(sql);
        try {        
            preparedStatement.setInt(1, x); 
            preparedStatement.setInt(2, y); 
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection2!= null) {
                dbConnection2.close();
            }
        }
    }
    
    public static void update_element_health(SpaceMarine element ,Integer health, Long element_id) throws SQLException {
        Connection dbConnection2 = null;
        dbConnection2 = Table_Data.getDBConnection();
        String sql = "UPDATE data SET HEALTH=? WHERE ID="+element_id+";";//
        PreparedStatement preparedStatement = dbConnection2.prepareStatement(sql);
        try {        	
            preparedStatement.setInt(1, health);                    
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection2!= null) {
                dbConnection2.close();
            }
        }
    }
}
