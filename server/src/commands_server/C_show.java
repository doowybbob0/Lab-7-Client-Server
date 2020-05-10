package commands_server;
import server.CCollection;

public class C_show {
	public String show() {
		StringBuilder reportString = new StringBuilder();
        int a = 1;
        long key = 0;
        if (CCollection.collection.size() != 0) {
            key++;           
            while (a <= CCollection.collection.size()) {
                if (CCollection.collection.get(key) != null) {
                    reportString.append("Key value: " + key + " | Name: " + CCollection.collection.get(key).getName() + " | Coordinates: (" + CCollection.collection.get(key).getCordinatesX() + ";" + CCollection.collection.get(key).getCordinatesY() + ")" + " | Health Points: " + CCollection.collection.get(key).getHealth() + " | Weapon Type: " + CCollection.collection.get(key).getWeapon() + " | Melee Weapon Type: " + CCollection.collection.get(key).getMeleeWeapon() + " | Chapter Description (" + CCollection.collection.get(key).getChapter().getLegion() + ";" + CCollection.collection.get(key).getChapter().getSquad() +" | AuthorID: "+CCollection.collection.get(key).getAuthor()+")");
                    reportString.append(System.lineSeparator());
                    key++;
                    a++;
                } else key++;
            }
        } else reportString.append("This collection is empty");
        return reportString.toString();
	}
	
}
