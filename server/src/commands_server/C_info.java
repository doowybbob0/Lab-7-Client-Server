package commands_server;

import server.CCollection;

public class C_info {
    public String info(){
        return ("Тип коллекции " + CCollection.collection.getClass() + "\nДата инициализации: " + CCollection.getTime() + "\nколичество элементов: " + CCollection.collection.entrySet().stream().mapToInt((p)->1).sum());//Collection.getCollection().size()
    }
}
