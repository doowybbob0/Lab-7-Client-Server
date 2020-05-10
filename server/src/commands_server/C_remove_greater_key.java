package commands_server;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import server.CCollection;
import server.TableDataFormatting;
import tables.Table_Data;

public class C_remove_greater_key {
	public Object remove_greater_key(Long key,Long user_id) {
		StringBuilder stringReport = new StringBuilder();
        if (CCollection.collection.size() != 0) {
            try {                
            	Connection dbConnection = Table_Data.getDBConnection();
                Statement statement = dbConnection.createStatement();                
                long element_id = key+1;
                ResultSet rs = statement.executeQuery("select max(id) from data");
                rs.next();
                int data_max_id = rs.getInt(1);
                
                while (element_id <= data_max_id) {
                    if (element_id <= data_max_id && CCollection.collection.get(element_id) != null) {
                    	if (user_id.equals(CCollection.collection.get(element_id).getAuthor())) {
                    		TableDataFormatting.delete_element(element_id);
                    		CCollection.getCollection().remove(element_id);
                    	}else {stringReport.append("Skipping element. You are not authorized to edit element ["+element_id+"]"); stringReport.append(System.lineSeparator());}
                    			
                    } //else {stringReport.append("No such key");stringReport.append(System.lineSeparator());}
                    element_id++;
                
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {stringReport.append("Cannot execute because collection is empty");stringReport.append(System.lineSeparator());}
        return stringReport.toString();
    }

}
