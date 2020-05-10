package client;
import java.io.Serializable;

import utils.Comand;

public class CommandsManager implements Serializable {
    Comand comand;
    int i;
    float aFloat;
    Long l;
    String string;

    public CommandsManager(Comand comand){
        this.comand=comand;
    }
    public CommandsManager(Comand comand,Long l){
        this.comand=comand;
        this.l=l;
    }
    public CommandsManager(Comand comand,String string){
        this.comand=comand;
        this.string=string;
    }
    public CommandsManager(Comand comand,int i){
        this.comand=comand;
        this.i=i;
    }
    public CommandsManager(Comand comand,Long l,int i){
        this.comand=comand;
        this.l=l;
        this.i=i;
    }
    public CommandsManager(Comand comand,Float fl){
        this.comand=comand;
        this.aFloat=fl;
    }

    public float getParam() { return aFloat; }
    public Comand getComand() {
        return comand;
    }
    public Long getL() {
        return l;
    }
    public String getString() { return string; }
    public int getI() { return i; }
}
