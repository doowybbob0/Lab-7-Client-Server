package server;
import java.io.*;
import java.time.Instant;
import java.util.*;
import com.google.gson.*;

import utils.Coordinates;
import utils.Keys;
import utils.SpaceMarine;

import java.util.Date;
import java.util.Objects;

/**
 * This class contains descriptions of possible input commands which class Control runs
 */
public class CommandInput {
    private static ArrayList<Integer> history = new ArrayList<Integer>();
    private static int h = 0;

    public void nul() {
        System.out.println("Enter new command or type help ");
    }
    String commandArray[]= {"help","info","show","insert key","update id","remove_key","clear","remove_lower","replace_if_lowe","remove_greater_key","min_by_creation_date","print"};
    public void help() {
        System.out.println("Available commands:");
        System.out.println("help : вывести справку по доступным командам");
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов");
        System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("insert_key {key} : добавить новый элемент с заданным ключом");
        System.out.println("update_id {element} : обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_key {key} : удалить элемент из коллекции по его ключу");
        System.out.println("clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("execute_script {file_name} : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        System.out.println("exit : завершить программу (без сохранения в файл)");
        System.out.println("history : вывести последние 14 команд (без их аргументов)");
        System.out.println("replace_if_lower {key} : заменить значение по ключу, если новое значение меньше старого");
        System.out.println("remove_greater_key {key} : удалить из коллекции все элементы, ключ которых превышает заданный");
        System.out.println("filter_greater_than_health {health} : вывести элементы, значение поля health которых больше заданного");
        System.out.println("print_field_ascending_health : вывести значения поля health всех элементов в порядке возрастания");
               
    }

    public void save(String path) {    	    
        long k = 1;
        SpaceMarineLists lists = new SpaceMarineLists();
        List lst = new ArrayList();
        while (k <= CCollection.collection.size()) {
            lst.add(CCollection.collection.get(k));
            k++;
        }
        lists.setSpaceMarineList(lst);
        String filepath = path+".json";
        PrintWriter pwDefault;
        try {       //JSON Conversion
        	if (path!=null) { pwDefault = new PrintWriter(new File(filepath));}
        	else { pwDefault = new PrintWriter(new File("Collection.json"));}
        	
        	String json = new Gson().toJson(lists);
        	pwDefault.print(json);
        	pwDefault.close();
        	System.out.println("Collection has been saved");
        } 
        catch(FileNotFoundException e1) {
        	System.out.println("Error has occured while saving the program");
        	e1.printStackTrace();
        }
        
    }

    
    
    public void insert_key(Scanner scanner,String value, Long author) { //moved to C_insert_key
    	System.out.println("Entered insert_key");
        long keys;
        keys = Keys.keys();
        System.out.println("Passed keys");
        
        try {
	        if (CCollection.collection.size() != 0) {
	        	int check=0;
	        	if (check==0) {
	        		
	        		long kk = Long.parseLong(value);	        	
	        		if (CCollection.collection.get(kk) == null) {
	        			keys=kk;
	        		} 	        		
	        		else {
	        			System.out.println("This key is not available");
	        			System.out.println("Your generated key is "+keys);            	
	        		}
	        	}
	        	else {
	        		System.out.println("Enter key");
	        		long kk = scanner.nextLong();	        	
	        		if (CCollection.collection.get(kk) == null) {
	        			keys=kk;
	        		} 	        		
	        		else {
	        			System.out.println("This key is not available");
	        			System.out.println("Your generated key is "+keys);            	
	        		}
	        	}
	        	
	        }        
	        SpaceMarine space = Adding.Scan2(scanner);
	        if (space != null) {
	            CCollection.collection.put(keys, space);
	        }
        }
        catch (Exception ex) {
            System.out.println("Input data is incorrect"); }

    }

    public void show() { //moved to C_show
        int a = 1;
        long key = 0;
        if (CCollection.collection.size() != 0) {
            key++;
            System.out.println("Here's all available elements:");
            while (a <= CCollection.collection.size()) {
                if (CCollection.collection.get(key) != null) {
                    System.out.println("Key value: " + key + " | Name: " + CCollection.collection.get(key).getName() + " | Coordinates: (" + CCollection.collection.get(key).getCordinatesX() + ";" + CCollection.collection.get(key).getCordinatesY() + ")" + " | Health Points: " + CCollection.collection.get(key).getHealth() + " | Weapon Type: " + CCollection.collection.get(key).getWeapon() + " | Melee Weapon Type: " + CCollection.collection.get(key).getMeleeWeapon() + " | Chapter Description (" + CCollection.collection.get(key).getChapter().getLegion() + ";" + CCollection.collection.get(key).getChapter().getSquad() + ")");
                    key++;
                    a++;
                } else key++;
            }
        } else System.out.println("Collection is empty");
    }

    public void no_show() {
        try {
            FileReader fr = new FileReader("Collection.json");
            Scanner ois = new Scanner(fr);
            while (ois.hasNextLine()) {
                System.out.println(ois.nextLine());
            }
            fr.close();
            ois.hasNextLine();
        } catch (IOException e) {
            System.out.println(CCollection.getCollection());
        }
    }

    public String toString() {
        return "Type of collection " + CCollection.collection.getClass() + "\nInitialization Time: " + CCollection.getTime() + "\nnumber of elements: " + CCollection.getCollection().size();
    }

    public void clear() {
        CCollection.getCollection().clear();
        System.out.print("Collection wiped out");
        System.out.println();
    }

    
    public void remove1(Scanner ACD,String value) {
        if (CCollection.collection.size() != 0) {
        	long kk = Long.parseLong(value);
            if (CCollection.collection.get(kk) != null) {
                CCollection.getCollection().remove(kk);
            } else System.out.println("No such key");
        } else System.out.println("Cannot execute this command with empty collection!");
    }

    public void print() {
        if (CCollection.collection.size() != 0) {
            List<Long> x = new ArrayList<Long>();
            long i = 1;
            long a = 1;
            while (a <= (CCollection.collection.size())) {
                if (CCollection.collection.get(i) != null) {
                    long j = i + 1;
                    while (j <= CCollection.collection.size()) {
                        if (CCollection.collection.get(j) != null) {
                            if (CCollection.collection.get(i).getChapter().getLegion() == CCollection.collection.get(j).getChapter().getLegion()) {
                                x.add(j);
                                j++;
                            } else j++;
                        } else j++;
                    }
                    if (x.contains(i) == false) {
                        System.out.println(CCollection.collection.get(i).getChapter().getLegion());
                    }
                    i++;
                    a++;
                } else i++;
            }
        } else System.out.println("Cannot execute because collection is empty");
    }

    public void print_field_ascending_health() {
    	System.out.println("Sorting...");
    	int a = 0;
        long key = 0;
        int primeHealth=1000000000;
        long phID;
        if (CCollection.collection.size() != 0) {
        	while(a!=1000) {
        		key=0;        		
	        	while(key!=10000) {
	            key++;            
	            	if (CCollection.collection.get(key) != null) {                        		
	            
	            		if (CCollection.collection.get(key).getHealth() == a) {
	            			System.out.println("Key value: " + key + " | Name: " + CCollection.collection.get(key).getName() + " | Coordinates: (" + CCollection.collection.get(key).getCordinatesX() + ";" + CCollection.collection.get(key).getCordinatesY() + ")" + " | Health Points: " + CCollection.collection.get(key).getHealth() + " | Weapon Type: " + CCollection.collection.get(key).getWeapon() + " | Melee Weapon Type: " + CCollection.collection.get(key).getMeleeWeapon() + " | Chapter Description (" + CCollection.collection.get(key).getChapter().getLegion() + ";" + CCollection.collection.get(key).getChapter().getSquad() + ")");
	            		}
	            	
	            	}
	            }
	        a++;
        	}
        System.out.println("Done");
        }else System.out.println("Collection is empty");
    }


    
    public void filter_greater_than_health(String value) {
        if (CCollection.collection.size() != 0) {
            long key = 1;           
            int actualValue=Integer.parseInt(value);
            if (CCollection.collection.get(key).getHealth()>actualValue) {
            	System.out.println("Key value: " + key + " | Name: " + CCollection.collection.get(key).getName() + " | Coordinates: (" + CCollection.collection.get(key).getCordinatesX() + ";" + CCollection.collection.get(key).getCordinatesY() + ")" + " | Health Points: " + CCollection.collection.get(key).getHealth() + " | Weapon Type: " + CCollection.collection.get(key).getWeapon() + " | Melee Weapon Type: " + CCollection.collection.get(key).getMeleeWeapon() + " | Chapter Description (" + CCollection.collection.get(key).getChapter().getLegion() + ";" + CCollection.collection.get(key).getChapter().getSquad() + ")");
            }
            if (key<1000) {
            key++;}
            
        } else System.out.println("Cannot execute because collection is empty");
    }

    public void remove2(Scanner Remove2) {
        if (CCollection.collection.size() != 0) {
            try {
                System.out.println("Enter key value");
                long msk = CCollection.collection.size();
                long kkk = Remove2.nextLong();
                while (kkk < msk + 1) {
                    if (kkk <= msk & (kkk >= 1)) {
                        CCollection.getCollection().remove(kkk);
                    } else System.out.println("No such key");
                    kkk++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else System.out.println("Cannot execute because collection is empty");
    }

    public void replace(Scanner Replace,String value,Long author) {
        if (CCollection.collection.size() != 0) {
            long key;
            key = Long.parseLong(value);
            if (key != 0) {
                System.out.println("What would you like to change?:1-Name;2-Coords;3-HP");
                int i = Replace.nextInt();
                if (i == 1) {
                    System.out.println("Enter new name");
                    String name2 = Replace.next();
                    if (name2.length() < CCollection.collection.get(key).getName().length()) {
                        SpaceMarine rep = new SpaceMarine(name2, new Coordinates(CCollection.collection.get(key).getCordinatesX(), CCollection.collection.get(key).getCordinatesY()), CCollection.collection.get(key).getHealth(), CCollection.collection.get(key).getHeight(), CCollection.collection.get(key).getWeapon(),CCollection.collection.get(key).getMeleeWeapon(), CCollection.collection.get(key).getChapter(), CCollection.collection.get(key).getId(),author);
                        CCollection.collection.put(key, rep);
                    }
                }
                if (i == 2) {
                    System.out.println("Enter new coordinates:");
                    int x2 = Replace.nextInt();
                    int y2 = Replace.nextInt();
                    if (x2 + y2 < CCollection.collection.get(key).getCoordinates()) ;
                    {
                        SpaceMarine rep = new SpaceMarine(CCollection.collection.get(key).getName(), new Coordinates(x2, y2), CCollection.collection.get(key).getHealth(), CCollection.collection.get(key).getHeight(), CCollection.collection.get(key).getWeapon(),CCollection.collection.get(key).getMeleeWeapon(), CCollection.collection.get(key).getChapter(), CCollection.collection.get(key).getId(),author);
                        CCollection.collection.put(key, rep);
                    }
                }
                if (i == 3) {
                    System.out.println("Enter new health points value:");
                    Integer hel = Replace.nextInt();
                    if (hel < CCollection.collection.get(key).getHealth()) ;
                    {
                        SpaceMarine rep = new SpaceMarine(CCollection.collection.get(key).getName(), new Coordinates(CCollection.collection.get(key).getCordinatesX(), CCollection.collection.get(key).getCordinatesY()), hel, CCollection.collection.get(key).getHeight(), CCollection.collection.get(key).getWeapon(),CCollection.collection.get(key).getMeleeWeapon(), CCollection.collection.get(key).getChapter(), CCollection.collection.get(key).getId(),author);
                        CCollection.collection.put(key, rep);
                    }
                }
            } else {
                System.out.println("No such key!" + " Here's a list of available keys:");
                System.out.println(CCollection.collection);
            }
        } else System.out.println("Unable to execute because of an empty collection");
    }

    public static void script(Scanner commandReader) {
        int sk=0;
        int c=0;
        ArrayList<String> list = new ArrayList<String>();
        h++;
        history.add(h);        
        if (history.size()<8) {
            System.out.println("Please, enter your script file name");
            String filik = commandReader.nextLine();
            try {
                FileReader fr = new FileReader(filik);
                Scanner ois = new Scanner(fr);
                
                String k = "";
                int i=0;
                while (ois.hasNextLine()){
                    k=ois.nextLine();
                    String[] finalUserCommand;
                    finalUserCommand = k.trim().split(" ", 2);
                    switch (finalUserCommand[0]) {
                        case "info":
                            System.out.println("["+java.util.Date.from(Instant.now())+"]:"+"Executing info");
                            System.out.println(Control.prompt.toString());
                            System.out.println("Done");
                            i++;
                            break;
                        case "help":
                            System.out.println("["+java.util.Date.from(Instant.now())+"]:"+"Executing help");
                            Control.prompt.help();
                            System.out.println("Done");
                            i++;
                            break;
                        /**case "insert_key":
                            sk = i;
                            System.out.println("["+java.util.Date.from(Instant.now())+"]:"+"Executing inset_key");
                            Control.prompt.insert_key(Control.commandReader,finalUserCommand[1]);
                            System.out.println("Done");
                            i = i + 22;
                            i++;
                            break;**/
                        case "show":
                            System.out.println("["+java.util.Date.from(Instant.now())+"]:"+"Executing  show");
                            Control.prompt.show();
                            System.out.println("Done");
                            i++;
                            break;
                        case "print_unique_chapter":
                            System.out.println("["+java.util.Date.from(Instant.now())+"]:"+"Executing print_unique_chapter ");
                            Control.prompt.print();
                            System.out.println("Done");
                            i++;
                            break;
                        case "remove_lower":
                            System.out.println("["+java.util.Date.from(Instant.now())+"]:"+"Executing remove_lower ");
                            Control.prompt.remove3(commandReader);
                            System.out.println("Done");
                            i++;
                            break;
                        case "remove_greater_key":
                            System.out.println("["+java.util.Date.from(Instant.now())+"]:"+"Executing remove_greater_key ");
                            Control.prompt.remove2(commandReader);
                            System.out.println("Done");
                            i++;
                            break;
                        case "max_by_coordinates":
                            System.out.println("["+java.util.Date.from(Instant.now())+"]:"+"Executing max_by_coordinates ");
                            Control.prompt.print_field_ascending_health();
                            System.out.println("Done");
                            i++;
                            break;
                        case "update_id":
                            System.out.println("["+java.util.Date.from(Instant.now())+"]:"+"Executing update_id ");
                            Control.prompt.update(commandReader,finalUserCommand[1]);
                            System.out.println("Done");
                            i++;
                            break;
                        case "min_by_creation_date":
                            System.out.println("["+java.util.Date.from(Instant.now())+"]:"+"Executing min_by_creation_date ");
                            Control.prompt.filter_greater_than_health(filik);
                            System.out.println("Done");
                            i++;
                            break;
                        case "remove_key":
                            System.out.println("["+java.util.Date.from(Instant.now())+"]:"+"Executing remove_key ");
                            Control.prompt.remove1(commandReader,finalUserCommand[1]);
                            i=i+2;
                            System.out.println("Done");
                            break;
                        /**case "replace_if_lowe":
                            System.out.println("["+java.util.Date.from(Instant.now())+"]:"+"Executing replace_if_lowe ");
                            Control.prompt.replace(commandReader,filik);
                            System.out.println("Done");
                            i++;
                            break;**/
                        case "execute_script":
                            System.out.println("["+java.util.Date.from(Instant.now())+"]:"+"Executing new script sequence");
                            Control.prompt.script(commandReader);
                            i++;
                            break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Corrupted script file ");
                System.out.println("Stopping script sequence");
            }
        }else System.out.println("script cannot run more than 7 times");
    }
    public void remove1sc(Scanner ACD,File filik,int i) throws FileNotFoundException {
        int sc=1;
        if (CCollection.collection.size() != 0) {
            Scanner scanner = new Scanner (filik);
            while (sc<i+1){
                scanner.nextLine();
                sc++;
            }
           long kk =scanner.nextLong();
            if (CCollection.collection.get(kk) != null) {
                CCollection.getCollection().remove(kk);
            } else System.out.println("No such key");
        } else System.out.println("Cannot execute because collection is empty");
    }



    public void update(Scanner Update, String value) {
        long key;
        key = Long.parseLong(value);
        if (CCollection.collection.size() != 0) {
	        if (key != 0) {
	            CCollection.collection.put(key, Upd.Scan4(Update));
	        } else {
	            long i = 1;
	            int n = CCollection.collection.size();
	            System.out.println("This ID do not exist; " + " Here are all available IDs: ");
	            while (i <= n) {
	                System.out.print(" ID" + CCollection.collection.get(i) + ":" + CCollection.collection.get(i).getId());
	                i++;
	            }
	        }
        }else System.out.println("Cannot execute because collection is empty");
    }

    public void remove3(Scanner Removel) {
        long key;
        long s = 1;
        long a = 1;
        long ii = 1;
        Iterator<Map.Entry<Long, SpaceMarine>> iterator = CCollection.collection.entrySet().iterator();
        if (CCollection.collection.size() != 0) {
            Map.Entry<Long, SpaceMarine> entry = iterator.next();
            while (ii != entry.getKey()) {
                ii++;
            }
            long n = CCollection.collection.size();
            System.out.println("Enter your key");
            key = Removel.nextLong();
            if (key != 0) {
                System.out.println("Choose values to compare:1-Name;2-Coordinates");
                int i = Removel.nextInt();
                if (i == 1) {
                    while (a <= n) {
                        if (CCollection.collection.get(ii) != null) {
                            if (CCollection.collection.get(ii).getName().length() < CCollection.collection.get(key).getName().length()) {
                                CCollection.collection.remove(ii);
                            } else ii++;
                        } else {
                            a++;
                            ii++;
                        }                                            
                    }
                }
                if (i == 2) {
                    while (a <= n) {
                        if (CCollection.collection.get(ii) != null) {
                            if (CCollection.collection.get(ii).getCoordinates() < CCollection.collection.get(key).getCoordinates()) {
                                CCollection.collection.remove(ii);
                            } else ii++;
                        } else {
                            a++;
                            ii++;

                        }
                    }
                }
            } else {
                System.out.println("No such key; " + " Here are all available keys: ");
                System.out.println(CCollection.collection);
            }
        } else System.out.println("Cannot execute because collection is empty");
    }

    /** !PREVIOUS BUILD
     * public void raw_start() throws FileNotFoundException{
    		System.out.println("Loading prerecorded collection...");
        	Main.loadJson("CollectionBackup.json");
        	long keys;
            while (CCollection.collectionBuffer.size()!=0){
                keys=Keys.keys();
                CCollection.collection.put(keys,CCollection.collectionBuffer.getFirst());
                CCollection.collectionBuffer.removeFirst();}
        }**/

}        
