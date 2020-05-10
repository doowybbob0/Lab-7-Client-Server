package commands_server;

import java.io.FileNotFoundException;

import server.CCollection;
import server.Main;
import utils.Keys;

public class C_raw_start {
	public static Object raw_start() throws FileNotFoundException{		
    	/**Main.loadJson("CollectionBackup.json");
    	long keys;
        while (CCollection.collectionBuffer.size()!=0){
            keys=Keys.keys();
            CCollection.collection.put(keys,CCollection.collectionBuffer.getFirst());
            CCollection.collectionBuffer.removeFirst();            
        }**/
        return "Loading prerecorded collection...";
    }
}
