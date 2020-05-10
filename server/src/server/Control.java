package server;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayDeque;

/**
 * This class process user's input
 */
public class Control {
    public static CommandInput prompt;
    private String userCommand;
    private String[] finalUserCommand;
    public static Scanner commandReader= new Scanner(System.in);
    public Control(CommandInput team) {
        Control.prompt = team;
    }
    
    /**
     * Queue that stores history of last 14 inputs
     */
    ArrayDeque<String> historyQueue = new ArrayDeque<String>();  //utility for history command
    
    
    /**
     * This method checks input and route commands
     */
    public void run() {
        try (Scanner commandReader = new Scanner(System.in)) {
            String userCommand = "";
            String[] finalUserCommand;
            while (!userCommand.equals("exit")) {
                userCommand = commandReader.nextLine();
                userCommand = userCommand.trim();
                finalUserCommand = userCommand.trim().split(" ", 2);// trim().split-находит слова убирая пробел

                try {
                	//=================================================================
                	                	
                	historyQueue.add(finalUserCommand[0]);
                	int k=0;
                	for(String i: historyQueue) {
                		k++;
                	}
                	if (k>14) {
                		historyQueue.poll();
                	}
                	
                	
                	//=================================================================
                    switch (finalUserCommand[0]) {
                        case "":
                            prompt.nul();    //======CHECK=======
                            break;
                        case "execute_script":		//======CHECK=======
                            prompt.script(Control.commandReader);
                            break;                                              
                        /**case "raw_start":		//======CHECK=======
                            prompt.raw_start();
                            break;**/
                        case "show":		//======CHECK=======
                            prompt.show();
                            break;
                        case "no_show":		//======CHECK=======
                            prompt.no_show();
                            break;
                        /**case "insert_key":		//======CHECK=======
                            prompt.insert_key(commandReader,finalUserCommand[1]);
                            prompt.show();
                            break;**/
                        case "print_unique_chapter"://print_unique_chapter
                            prompt.print();
                            break;
                        case "remove_lower"://remove_greater_key
                            prompt.remove3(commandReader);
                            break;
                        case "remove_greater_key"://remove_lower
                            prompt.remove2(commandReader);		//======CHECK=======
                            prompt.show();
                            break;
                        case "print_field_ascending_health"://max_by_coordinates
                            prompt.print_field_ascending_health();
                            break;
                        case "update_id":		//======CHECK=======
                            prompt.update(commandReader,finalUserCommand[1]);
                            prompt.show();
                            break;
                        case "filter_greater_than_health"://======CHEK=========
                            prompt.filter_greater_than_health(finalUserCommand[1]);
                            break;
                        case "remove_key"://remove_greater_key
                            prompt.remove1(commandReader,finalUserCommand[1]);		//======CHECK=======
                            break;
                        /**case "replace_if_lower":		//======CHECK=======
                            prompt.replace(commandReader,finalUserCommand[1]);
                            break;**/
                        case "clear":
                            prompt.clear();		//======CHECK=======
                            break;
                        case "info":		//======CHECK=======
                            System.out.println(prompt.toString());
                            break;
                        case "help":		//======CHECK=======
                            prompt.help();
                            break;
                        case "save":                        	
                            prompt.save(finalUserCommand[1]);		//======CHECK=======
                            break;
                        case "exit":		//======CHECK=======
                        	System.out.println("Beginning Self-Destruction Sequence...");
                        	System.out.println("Program destroyed successfully");
                            break;
                        case "history":
                        	System.out.println("Here's your input history:");
                        	for(String i: historyQueue)
                                System.out.println(i);
                        	break;
                        
                        
                        default:
                            System.out.println("Unknown command. Type help for list of commands ");
                    }
                } catch (ArrayIndexOutOfBoundsException   ex) {
                	System.out.println("Wrong arguements! Type [help] to see the list of commands");
                    System.out.println("Waiting for input");
                }
            }
        }
    }
}
