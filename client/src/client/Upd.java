package client;
import java.io.Serializable;
import java.util.Scanner;

import utils.Chapter;
import utils.Coordinates;
import utils.MeleeWeapon;
import utils.SpaceMarine;
import utils.Weapon;

/**
 * This class serves for updating elements of collection
 */
public class Upd implements Serializable {
    /**public static long Scan3(Scanner Update) {
        try {
            long iiid=0;
            System.out.println("Enter value ");
            long iid = Update.nextInt();
            int i = 1;
            long k = 1;
            while (i <= CCollection.collection.size()) {
                if (iid == CCollection.collection.get(k).getId()) {
                    iiid = iid;
                    long kk = k; }
                k++;
                i++; }
            return iiid;
        }
        catch (Exception ex) {
            System.out.println("Input data is incorrect"); }
        return 0;
    }**/
        public static SpaceMarine Scan4(Scanner Update) {
                try {
                    System.out.print("Enter name: ");
                    String name = Update.nextLine();
                    while (name.equals("")) {
                        System.out.println("Name is incorrect. Enter actual name:");
                        name = Update.next();
                    }
                    System.out.print("Enter X coordinate: ");
                    Integer x = Update.nextInt();
                    System.out.print("Enter Y coordinate: ");
                    Integer y = Update.nextInt();
                    while (y>935) {
                        System.out.println("Y coordinate can only be less than 935");
                        y=Update.nextInt();}
            System.out.print("Enter Health Points: ");
            int healthInt = Update.nextInt();
            Update.nextLine();
                    while (healthInt<=0){
                        System.out.println("Health must be greater than 0. Type correct amount: ");
                        healthInt = Update.nextInt();
                    }

            System.out.print("Enter height: ");
            Integer heightInt = Update.nextInt();
            Update.nextLine();
            System.out.print("Choose your hero's weapon (number)(1.PLASMA GUN|" +
                    "2.FLAMER|" +
                    "3.GRAV GUN|" +
                    "4.GRENADE LAUNCHER|" +
                    "5.MULTI MELTA): ");
            Integer in = Update.nextInt();
                    while (in>5 | in<1 | in==null){
                        System.out.println("Incorrect number(1 to 5):");
                        System.out.print("Choose your hero's weapon (number)(1.PLASMA GUN|" +
                                "2.FLAMER|" +
                                "3.GRAV GUN|" +
                                "4.GRENADE LAUNCHER|" +
                                "5.MULTI MELTA): ");
                        in = Update.nextInt();
                    }
            Weapon categ = null;
            if (in == 1) {  categ = Weapon.PLASMA_GUN; }
            if (in == 2) { categ = Weapon.FLAMER; }
            if (in == 3) { categ = Weapon.GRAV_GUN; }
            if (in == 4) { categ = Weapon.GRENADE_LAUNCHER; }
            if (in == 5) { categ = Weapon.MULTI_MELTA; }
            Weapon categ1 = categ;
            
            Update.nextLine();
            System.out.print("Choose your Melee Weapon (number)(1.CHAIN SWORD|" +
                    "2.MANREAPER|" +
                    "3.POWER BLADE): ");
            Integer in1 = Update.nextInt();
                    while (in>3 | in<1 | in==null){
                        System.out.println("Incorrect number(1 to 3):");
                        System.out.print("Choose your Melee Weapon (number)(1.CHAIN SWORD|" +
                                "2.MANREAPER|" +
                                "3.POWER BLADE): ");
                        in1 = Update.nextInt();
                    }
            MeleeWeapon mwcateg = null;
            if (in1 == 1) {  mwcateg = MeleeWeapon.CHAIN_SWORD; }
            if (in1 == 2) { mwcateg = MeleeWeapon.MANREAPER; }
            if (in1 == 3) { mwcateg = MeleeWeapon.POWER_BLADE; }
            MeleeWeapon categ2 = mwcateg;
            
            Update.nextLine();
            System.out.print("Enter squad name: ");
            String name2 = Update.nextLine();
                    while (name2.equals("")) {
                        System.out.println("Please enter actual name");
                        name2 = Update.nextLine();
                    }
            System.out.print("Enter Legion name: ");
            String name3 = Update.nextLine();
                    while (name3.equals("")) {
                        System.out.println("Please enter actual name");
                        name3 = Update.next();
                    }            
            
            SpaceMarine abcd = new SpaceMarine(name, new Coordinates(x, y), healthInt, heightInt, categ1,categ2, new Chapter(name2, name3));
            return abcd;
        }
        catch (Exception ex) {
            System.out.println("Input data is incorrect");
        }
        return null;
    }
}
