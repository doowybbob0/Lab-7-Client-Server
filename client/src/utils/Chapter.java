package utils;
import java.io.Serializable;
import java.util.Objects;
/**
 *  Chapter value description
 */

public class Chapter implements Serializable {
        private String legion; //Поле не может быть null, Строка не может быть пустой
        private String squad;
    public Chapter(){}
    
    public  Chapter(String arg_legion,String arg_squad){
        this.legion=arg_legion;
        this.squad=arg_squad;
    }


    public String getLegion(){
        return legion;
    }

    public String getSquad(){
        return squad;
    }
    @Override
    public int hashCode() {
        return Objects.hash(legion, squad);
    }
}
