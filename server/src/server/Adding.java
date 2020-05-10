package server;
import java.io.Serializable;
import java.util.Scanner;

import utils.Chapter;
import utils.Coordinates;
import utils.MeleeWeapon;
import utils.SpaceMarine;
import utils.Weapon;
/**
 * Этот класс используется в методе insert_key
 */
public class Adding implements Serializable {
	
	
    public static SpaceMarine Scan2(Scanner scan) {
        try {
        	
            System.out.print("Enter name: ");
            System.out.println();
            String name = scan.nextLine();
            while (name.equals("")){
                System.out.println("Name is incorrect. Type actual name");
                name=scan.next();
            }
            System.out.print("Enter X coordinate: ");
            Integer x = scan.nextInt();
            System.out.print("Enter Y coordinate: ");
            Integer y = scan.nextInt();
            while (y>935) {
                System.out.println("Y coordinate can only be less than 935");
                y=scan.nextInt();}
            System.out.print("Enter Health Points: ");
            Integer healthInt = scan.nextInt();
            scan.nextLine();
            while (healthInt<=0){
                System.out.println("Health Points value must be greater than 0");
                healthInt = scan.nextInt();
            }                        
            System.out.print("Enter height: ");
            Integer heightInt = scan.nextInt();
            scan.nextLine();
            System.out.print("Choose your hero's weapon (number)(1.PLASMA GUN|" +
                    "2.FLAMER|" +
                    "3.GRAV GUN|" +
                    "4.GRENADE LAUNCHER|" +
                    "5.MULTI MELTA): ");
            Integer in = scan.nextInt();            
            while (in>5 | in<1 | in==null){
                System.out.println("Incorrect number(1 to 5):");
                System.out.print("Choose your hero's weapon (number)(1.PLASMA GUN|" +
                        "2.FLAMER|" +
                        "3.GRAV GUN|" +
                        "4.GRENADE LAUNCHER|" +
                        "5.MULTI MELTA): ");
                in = scan.nextInt();
            }
            Weapon categ = null;
            if (in == 1) {  categ = Weapon.PLASMA_GUN; }
            if (in == 2) { categ = Weapon.FLAMER; }
            if (in == 3) { categ = Weapon.GRAV_GUN; }
            if (in == 4) { categ = Weapon.GRENADE_LAUNCHER; }
            if (in == 5) { categ = Weapon.MULTI_MELTA; }
            Weapon categ1 = categ;
            
            scan.nextLine();
            System.out.print("Choose your Melee Weapon (number)(1.CHAIN SWORD|" +
                    "2.MANREAPER|" +
                    "3.POWER BLADE): ");
            Integer in1 = scan.nextInt();
                    while (in1>3 | in1<1){
                        System.out.println("Wrong number, please enter correct one(1 to 3):");
                        System.out.print("Choose your Melee Weapon (number)(1.CHAIN SWORD|" +
                                "2.MANREAPER|" +
                                "3.POWER BLADE): ");
                        in1 = scan.nextInt();
                    }
            MeleeWeapon mwcateg = null;
            if (in1 == 1) {  mwcateg = MeleeWeapon.CHAIN_SWORD; }
            if (in1 == 2) { mwcateg = MeleeWeapon.MANREAPER; }
            if (in1 == 3) { mwcateg = MeleeWeapon.POWER_BLADE; }
            MeleeWeapon categ2 = mwcateg;
            
            
            
            scan.nextLine();
            System.out.print("Enter squad name: ");
            String name2 = scan.nextLine();
            while (name2.equals("")) {
                System.out.println("Please enter actual name");
                name2 = scan.nextLine();
            }
            System.out.print("Enter Legion name: ");
            String name3 = scan.nextLine();
            while (name3.equals("")) {
                System.out.println("Please enter actual name");
                name3 = scan.next();
            }
            
            
            SpaceMarine finalSpaceMarine = new SpaceMarine(name, new Coordinates(x, y), healthInt, heightInt, categ1,categ2, new Chapter(name2, name3));
            return finalSpaceMarine;
        }
        catch (Exception ex) {
            System.out.println("Input data is incorrect");
        }
        return null;
    }
}