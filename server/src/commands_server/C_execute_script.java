package commands_server;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

import server.CCollection;
import server.Control;
import utils.Keys;
import utils.SpaceMarine;

public class C_execute_script  {
	private ArrayList<Integer> history = new ArrayList<Integer>();
	
	public Object script(Scanner commandReader, String filePath) throws FileNotFoundException{
		StringBuilder scriptString = new StringBuilder();
	    int h = 0;
        int sk=0;
        int c=0;
        ArrayList<String> list = new ArrayList<String>();
        h++;
        history.add(h);        
        if (history.size()<8) {
            
            String filik = filePath;
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
                            scriptString.append("["+java.util.Date.from(Instant.now())+"]:"+"Executing info");
                            scriptString.append(System.lineSeparator());
                            Object info = new C_info().info();
                            scriptString.append(info);
                            scriptString.append(System.lineSeparator());
                            scriptString.append("Done");
                            scriptString.append(System.lineSeparator());
                            i++;
                            break;
                        case "help":
                        	scriptString.append("["+java.util.Date.from(Instant.now())+"]:"+"Executing help");
                        	scriptString.append(System.lineSeparator());
                        	Object help = new C_help().help();
                            scriptString.append(help);
                            scriptString.append(System.lineSeparator());
                            scriptString.append("Done");
                            scriptString.append(System.lineSeparator());
                            i++;
                            break;
                        /**case "insert_key":
                            sk = i;
                            long keys;
                            keys = Keys.keys();
                            scriptString.append("["+java.util.Date.from(Instant.now())+"]:"+"Executing inset_key");
                            scriptString.append(System.lineSeparator());
                            System.out.println(finalUserCommand[0]);
                            System.out.println(finalUserCommand[1]);
                            Control.prompt.insert_key(commandReader,finalUserCommand[1]);
                            //scriptString.append(inskey);
                            scriptString.append(System.lineSeparator());
                            scriptString.append("Done");
                            scriptString.append(System.lineSeparator());
                            i = i + 22;
                            i++;
                            break;**/
                        case "show":
                        	scriptString.append("["+java.util.Date.from(Instant.now())+"]:"+"Executing  show");
                        	scriptString.append(System.lineSeparator());
                            Object show = new C_show().show();
                            scriptString.append(show);
                            scriptString.append(System.lineSeparator());
                            scriptString.append("Done");
                            scriptString.append(System.lineSeparator());
                            i++;
                            break;
                        
                        /***case "remove_greater_key":
                        	Long rgk_key = Long.parseLong(finalUserCommand[1]);
                        	scriptString.append("["+java.util.Date.from(Instant.now())+"]:"+"Executing remove_greater_key ");
                        	scriptString.append(System.lineSeparator());
                            Object rgk = new C_remove_greater_key().remove_greater_key(rgk_key);
                            scriptString.append(rgk);
                            scriptString.append(System.lineSeparator());
                            scriptString.append("Done");
                            scriptString.append(System.lineSeparator());
                            i++;
                            break;**/
                        case "print_field_ascending_health":
                        	scriptString.append("["+java.util.Date.from(Instant.now())+"]:"+"Executing max_by_coordinates ");
                        	scriptString.append(System.lineSeparator());
                            Object pfah = new C_print_field_ascending_health().print_field_ascending_health();
                            scriptString.append(pfah);
                            scriptString.append(System.lineSeparator());
                            scriptString.append("Done");
                            scriptString.append(System.lineSeparator());
                            i++;
                            break;
                        /**case "update_id":
                        	scriptString.append("["+java.util.Date.from(Instant.now())+"]:"+"Executing update_id ");
                        	scriptString.append(System.lineSeparator());                        	                        	                        	
                            Control.prompt.update(commandReader,finalUserCommand[1]);
                            scriptString.append("Done");
                            scriptString.append(System.lineSeparator());
                            i++;
                            break;**/
                        case "filter_greater_than_health":
                        	scriptString.append("["+java.util.Date.from(Instant.now())+"]:"+"Executing min_by_creation_date ");
                        	scriptString.append(System.lineSeparator());                        	
                            Object fgth = new C_filter_greater_than_health().filter_greater_than_health(finalUserCommand[1]);
                            scriptString.append(fgth);
                            scriptString.append("Done");
                            scriptString.append(System.lineSeparator());
                            i++;
                            break;
                        case "remove_key":
                        	scriptString.append("["+java.util.Date.from(Instant.now())+"]:"+"Executing remove_key ");
                        	scriptString.append(System.lineSeparator());
                            Object rk = new C_remove_key().remove1(finalUserCommand[1]);
                            scriptString.append(rk);
                            scriptString.append(System.lineSeparator());
                            scriptString.append("Done");
                            scriptString.append(System.lineSeparator());
                            i=i+2;
                            
                            break;
                        /**case "replace_if_lower":
                        	scriptString.append("["+java.util.Date.from(Instant.now())+"]:"+"Executing replace_if_lowe ");
                        	scriptString.append(System.lineSeparator());
                            Control.prompt.replace(commandReader,finalUserCommand[1]);
                            scriptString.append("Done");
                            scriptString.append(System.lineSeparator());
                            i++;
                            break;**/
                        case "execute_script":
                        	scriptString.append("["+java.util.Date.from(Instant.now())+"]:"+"Executing new script sequence");
                        	scriptString.append(System.lineSeparator());
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
        return scriptString;
    }
}
