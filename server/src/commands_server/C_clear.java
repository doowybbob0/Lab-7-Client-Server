package commands_server;

import server.CCollection;

public class C_clear {
	public static Object clear() {
        CCollection.getCollection().clear();
        return "Collection wiped out";        
    }
}
