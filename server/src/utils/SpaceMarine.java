package utils;

import java.time.Instant;
import java.io.*;
import java.util.Date;
import java.util.Objects;

/**
 * Description of Space Marine fields and methods
 */
public class SpaceMarine implements Serializable{
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer health; //Поле может быть null, Значение поля должно быть больше 0
    private Integer height; //Поле может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Weapon weaponType; //Поле не может быть null
    private Chapter chapter; //Поле не может быть null
    private Long author; //Owner of element

    public SpaceMarine() {
    }

    public SpaceMarine(String name, Coordinates coordinates1, int health, Integer height2, Weapon wcategory, MeleeWeapon mwcategory, Chapter chapter1,Long id,Long author) {
        this.name = name;
        this.coordinates = coordinates1;
        creationDate =  java.util.Date.from(Instant.now());
        this.health = health;
        this.height = height2;
        this.weaponType = wcategory;
        this.meleeWeapon = mwcategory;
        this.chapter = chapter1;
        this.id=id;
        this.author=author;
    }

    
    public SpaceMarine(String name, Coordinates coordinates1, int health, Integer height2, Weapon wcategory, MeleeWeapon mwcategory, Chapter chapter1) {
        this.name = name;
        this.coordinates = coordinates1;
        creationDate =  java.util.Date.from(Instant.now());
        this.health = health;
        this.height = height2;
        this.weaponType = wcategory;
        this.meleeWeapon = mwcategory;
        this.chapter = chapter1;

    }

    public int getCoordinates() {
        return this.coordinates.getX() + this.coordinates.getY();
    }

    public int getCordinatesX(){return this.coordinates.getX();}

    public int getCordinatesY(){return this.coordinates.getY();}

    public Date getDate() {
        return creationDate;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return weaponType;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }
    
    public long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, health, coordinates, meleeWeapon, chapter, height, id);
    }

    @Override
    public String toString() {
        return name;
    }

    public Date getCreationDate() {
        return creationDate;
    }


    public int getHeight() {
        return height;
    }

    public Chapter getChapter() {
        return chapter;
    }
    
    public Long getAuthor() {
    	return author;
    }
}
