package commands_server;
import server.CCollection;

public class C_remove_key {
	public static Object remove1(String value) {
        if (CCollection.collection.size() != 0) {
        	long kk = Long.parseLong(value);
            if (CCollection.collection.get(kk) != null) {
            	CCollection.getCollection().remove(kk);
               return "Element removed";
            } else System.out.println("No such key");
        } else System.out.println("Cannot execute this command with empty collection!");
        return "An Error has occured during execution";
    }
}
