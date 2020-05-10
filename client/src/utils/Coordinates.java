package utils;
import java.io.Serializable;

/**
 * Этот класс описывает координаты
 */
public class Coordinates implements Serializable {
    private int x; //Поле не может быть null
    private int y; //Значение поля должно быть больше -463

    public Coordinates(){}
    public Coordinates(int x1, int y1) {
        x = x1;
        y = Math.max(y1, -463);
        }

    public int getX() {
        return x;
    }
    public int getY() {return y;}
    public int getCor(){return x+y; }
}