package utils;

import server.CCollection;

/**
 * С помощью этого класса каждому объекты коллекции создается свой уникальный ключ
 */
public class Keys {
    public static long keys() {
        int i = 0;
        long id = 0;
        while (i <= CCollection.collection.size() ) {
            i++;
            id = id + 1;
        }
        return id;
    }
    
    public static long keys(String key) {
        int i = 0;
        long id = 0;
        while (i <= CCollection.collection.size() ) {
            i++;
            id = id + 1;
        }
        return id;
    }
    
}